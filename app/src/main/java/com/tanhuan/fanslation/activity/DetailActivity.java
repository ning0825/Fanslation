package com.tanhuan.fanslation.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.print.PageRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.IcibaBean;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.entity.ParaEntity_;
import com.tanhuan.fanslation.mvp.IView;
import com.tanhuan.fanslation.mvp.ParaPresenter;
import com.tanhuan.fanslation.util.Constants;
import com.tanhuan.fanslation.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class DetailActivity extends AppCompatActivity implements IView<ParaBean> {
    String key;

    ParaPresenter paraPresenter;

    TextView tvPhone;
    ImageButton ibSpeaker;
    ImageButton ibNote;
    TabLayout tbDetail;
    ViewPager vpDetail;
    Toolbar toolbarDetail;
    List<Fragment> fragments;

    SharedPreferences sp;

    Box<ParaEntity> paraBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        tvPhone = findViewById(R.id.tv_phone);
        tbDetail = findViewById(R.id.tb_detail);
        vpDetail = findViewById(R.id.vp_detail);
        ibSpeaker = findViewById(R.id.ib_speaker);
        ibNote = findViewById(R.id.ib_note);

        paraPresenter = new ParaPresenter(this);
        toolbarDetail = findViewById(R.id.toolbar_detail);

        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sp = getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);

        Intent intent = getIntent();
        if (intent != null) {
            key = intent.getStringExtra("key");
        }

        paraBox = BaseApp.getBoxStore().boxFor(ParaEntity.class);
        ibNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = new EditText(DetailActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle("add note")
                        .setView(editText)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ParaEntity paraEntity = paraBox.query().equal(ParaEntity_.input, key).build().find().get(0);
                                paraEntity.setNote(editText.getText().toString());
                                paraBox.put(paraEntity);
                            }
                        })
                        .show();


            }
        });

        paraPresenter.request(key);
    }

    class VpAdater extends FragmentPagerAdapter {

        VpAdater(FragmentManager fm) {
            super(fm);;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return new String[]{"standard", "collins"}[position];
        }
    }


    @Override
    public void showResult(ParaBean paraBean) {
        tvPhone.setText(paraBean.getEc().getWord().get(0).getUsphone());
        toolbarDetail.setTitle(paraBean.getInput());

        //viewPager init
        Fragment fragment = new StandandFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("para", paraBean);
        fragment.setArguments(bundle);

        fragments = new ArrayList<>();
        if (!fragment.isAdded()) {
            fragments.add(fragment);
        }

        if (paraBean.getCollins() != null) {
            Fragment fragment1 = new CollinsFragment();
            fragment1.setArguments(bundle);
            fragments.add(fragment1);
        }

        VpAdater vpAdater = new VpAdater(getSupportFragmentManager());
        vpDetail.setAdapter(vpAdater);
        tbDetail.setupWithViewPager(vpDetail);

        ibSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        ibSpeaker.setEnabled(false);
                        IcibaBean icibaBean = HttpUtil.getIciba(key);
                        String speakUrl = icibaBean.getSymbols().get(0).getPh_am_mp3();
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(speakUrl);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener((mp -> ibSpeaker.setEnabled(true)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        save(paraBean);
    }

    @Override
    public void showError(Throwable throwable) {
//        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    void save(ParaBean paraBean) {
        long bookId = sp.getLong(Constants.SP_DEFAULT_BOOK_ID_KEY, 1);
        paraPresenter.save(paraBean, bookId,this);
    }
}

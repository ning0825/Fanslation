package com.tanhuan.fanslation.activity;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.UnrollViewPager;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import okhttp3.internal.Util;

public class ReciteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "----ReciteActivity----";

    int whichBook = 1;

    Box<BookEntity> bookBox;
    BookEntity bookEntity;
    List<ParaEntity> paras;

    List<View> views;

    Toolbar toolbar;
    UnrollViewPager vp;
    MyAdapter myAdapter;
    Button btForget;
    Button btRemember;
    ActionBar actionBar;

    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);

        btForget = findViewById(R.id.bt_forget);
        btRemember = findViewById(R.id.bt_remember);
        btForget.setOnClickListener(this);
        btRemember.setOnClickListener(this);

        bookBox = BaseApp.getBoxStore().boxFor(BookEntity.class);
        bookEntity = bookBox.get(whichBook);
        paras = bookEntity.toManyTransEntities;

        //initialize toolbar
        toolbar = findViewById(R.id.tb_recite);
        setSupportActionBar(toolbar);
        if ((actionBar = getSupportActionBar()) != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener((v -> onBackPressed()));

        //initialize viewPager
        gson = new Gson();
        vp = findViewById(R.id.vp_recite);
        views = new ArrayList<>();
        for (ParaEntity paraEntity : paras) {
            View view = getLayoutInflater().inflate(R.layout.item_vp_recite, null);
            ((TextView) view.findViewById(R.id.tv_recite_input)).setText(paraEntity.getInput());

            String trans = paraEntity.getTrans();
            ((TextView) view.findViewById(R.id.tv_recite_para)).setText(trans);

            views.add(view);
        }
        myAdapter = new MyAdapter(views);
        vp.setAdapter(myAdapter);
        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("touchtest", "onClick: ");
                View view = views.get(vp.getCurrentItem());
                float paraY = view.findViewById(R.id.tv_recite_para).getY();
                TextView tvInput = view.findViewById(R.id.tv_recite_input);
                TextView tvPara = view.findViewById(R.id.tv_recite_para);
                ObjectAnimator transInput = ObjectAnimator.ofFloat(tvInput, View.Y, tvInput.getY(), paraY - tvInput.getHeight() - ViewUtil.dp2px(ReciteActivity.this, 20));
                ObjectAnimator alphaPara = ObjectAnimator.ofFloat(tvPara, View.ALPHA, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(transInput, alphaPara);
                set.start();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_forget:
                break;
            case R.id.bt_remember:
                if (vp.getCurrentItem() == views.size() - 1) {
                    Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
                } else {
                    vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                }
                break;
            default:
                break;
        }
    }

    class MyAdapter extends PagerAdapter {
        List<View> vs;

        public MyAdapter(List<View> vs) {
            this.vs = vs;
        }

        @NonNull
        @Override
        public View instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(vs.get(position));
            return vs.get(position);
        }

        @Override
        public int getCount() {
            return vs.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(((View) object));
        }
    }
}

package com.tanhuan.fanslation.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.customview.UnrollViewPager;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.DataEntity;
import com.tanhuan.fanslation.entity.DataEntity_;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.util.Constants;
import com.tanhuan.fanslation.util.ViewUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.objectbox.Box;

public class ReciteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "----ReciteActivity----";

    long whichBook;

    Box<BookEntity> bookBox;
    BookEntity bookEntity;
    List<ParaEntity> initParas;
    List<ParaEntity> maskParas;
    ParaEntity currentPara;

    Box<DataEntity> dataBox;
    DataEntity dataEntity;
    List<DataEntity> datas;

    Box<ParaEntity> paraBox;

    List<View> views;

    Toolbar toolbar;
    UnrollViewPager vp;
    MyAdapter myAdapter;
    Button btForget;
    Button btRemember;
    Button btEasy;
    ActionBar actionBar;

    Gson gson;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);

        btForget = findViewById(R.id.bt_forget);
        btRemember = findViewById(R.id.bt_remember);
        btEasy = findViewById(R.id.bt_easy);
        btForget.setOnClickListener(this);
        btRemember.setOnClickListener(this);
        btEasy.setOnClickListener(this);

        bookBox = BaseApp.getBoxStore().boxFor(BookEntity.class);
        whichBook = getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE).getLong(Constants.SP_CURRENT_BOOK_ID_KEY, 0);
        bookEntity = bookBox.get(whichBook);
        initParas = bookEntity.toManyTransEntities;
        maskParas = getMaskPara(initParas);

        dataBox = BaseApp.getBoxStore().boxFor(DataEntity.class);
        datas = dataBox.query().equal(DataEntity_.date, ViewUtil.getDate()).build().find();
        if (datas.size() == 0) {
            dataEntity = new DataEntity(ViewUtil.getDate());
            dataBox.put(dataEntity);
        } else {
            dataEntity = datas.get(0);
        }

        paraBox = BaseApp.getBoxStore().boxFor(ParaEntity.class);


        //initialize toolbar
        toolbar = findViewById(R.id.tb_recite);
        setSupportActionBar(toolbar);
        if ((actionBar = getSupportActionBar()) != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        //initialize viewPager
        gson = new Gson();
        vp = findViewById(R.id.vp_recite);
        views = new ArrayList<>();
        for (ParaEntity paraEntity : maskParas) {
            View view = getLayoutInflater().inflate(R.layout.item_vp_recite, null);
            ((TextView) view.findViewById(R.id.tv_recite_input)).setText(paraEntity.getInput());

            TextView tvReciteNote = view.findViewById(R.id.tv_recite_note);
            if (!paraEntity.getNote().equals("")) {
                tvReciteNote.setVisibility(View.VISIBLE);
                tvReciteNote.setText(paraEntity.getNote());
            }

            String trans = paraEntity.getTrans();
            ((TextView) view.findViewById(R.id.tv_recite_para)).setText(trans);

            views.add(view);
        }
        myAdapter = new MyAdapter(views);
        vp.setAdapter(myAdapter);
        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vp.getTouchCount() == 0) {
                    View view = views.get(vp.getCurrentItem());
                    if (view.findViewById(R.id.tv_recite_note).getVisibility() == View.GONE) {
                        TextView tvInput = view.findViewById(R.id.tv_recite_input);
                        TextView tvPara = view.findViewById(R.id.tv_recite_para);
                        float paraY = tvPara.getY();
                        ObjectAnimator transInput = ObjectAnimator.ofFloat(tvInput, View.Y, tvInput.getY(), paraY - tvInput.getHeight() - ViewUtil.dp2px(ReciteActivity.this, 20));
                        ObjectAnimator alphaPara = ObjectAnimator.ofFloat(tvPara, View.ALPHA, 1);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(transInput, alphaPara);
                        set.start();

                        vp.setTouchCount(2);
                    } else {
                        TextView tvInput = view.findViewById(R.id.tv_recite_input);
                        TextView tvNote = view.findViewById(R.id.tv_recite_note);
                        float noteY = tvNote.getY();
                        ObjectAnimator transInput = ObjectAnimator.ofFloat(tvInput, View.Y, tvInput.getY(), noteY - tvInput.getHeight() - ViewUtil.dp2px(ReciteActivity.this, 20));
                        ObjectAnimator alphaPara = ObjectAnimator.ofFloat(tvNote, View.ALPHA, 1);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(transInput, alphaPara);
                        set.start();

                        vp.setTouchCount(1);
                    }

                } else if (vp.getTouchCount() == 1){
                    View view = views.get(vp.getCurrentItem());
                    TextView tvInput = view.findViewById(R.id.tv_recite_input);
                    TextView tvPara = view.findViewById(R.id.tv_recite_para);
                    TextView tvNote = view.findViewById(R.id.tv_recite_note);
                    float paraY = tvPara.getY();
                    ObjectAnimator transInput = ObjectAnimator.ofFloat(tvInput, View.Y, tvInput.getY(), paraY - tvInput.getHeight() - tvNote.getHeight() - 2 * ViewUtil.dp2px(ReciteActivity.this, 20));
                    ObjectAnimator transNote = ObjectAnimator.ofFloat(tvNote, View.Y, tvNote.getY(), paraY - tvNote.getHeight() - ViewUtil.dp2px(ReciteActivity.this, 20));
                    ObjectAnimator alphaPara = ObjectAnimator.ofFloat(tvPara, View.ALPHA, 1);
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(transInput, alphaPara,transNote);
                    set.start();

                    vp.setTouchCount(2);
                } else if (vp.getTouchCount() == 2) {
                    toDetailActivity(maskParas.get(vp.getCurrentItem()).getInput());
                }
            }
        });
    }

    void toDetailActivity(String key) {
        Intent intent = new Intent(ReciteActivity.this, DetailActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<ParaEntity> getMaskPara(List<ParaEntity> paraEntities) {
        List<ParaEntity> pes = new ArrayList<>();

        //exclude easy para
        for (ParaEntity para : paraEntities) {
            if (!para.isEasy()) {
                pes.add(para);
            }
        }
        // sort ParaEntity from smallest remember count to largest
        pes.sort(new Comparator<ParaEntity>() {
            @Override
            public int compare(ParaEntity o1, ParaEntity o2) {
                if (o1.getRemeberCount() > o2.getRemeberCount()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        return pes;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_forget:
                currentPara = maskParas.get(vp.getCurrentItem());
                currentPara.setRemeberCount(currentPara.getRemeberCount() - 1);
                paraBox.put(currentPara);

                if (vp.getCurrentItem() == views.size() - 1) {
                    Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
                } else {
                    vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                }

                dataEntity.setReciteNum(dataEntity.getReciteNum() + 1);

                vp.setTouchCount(0);
                break;
            case R.id.bt_remember:
                currentPara = maskParas.get(vp.getCurrentItem());
                currentPara.setRemeberCount(currentPara.getRemeberCount() + 1);
                paraBox.put(currentPara);

                if (vp.getCurrentItem() == views.size() - 1) {
                    Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
                } else {
                    vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                }

                dataEntity.setReciteNum(dataEntity.getReciteNum() + 1);

                vp.setTouchCount(0);
                break;
            case R.id.bt_easy:
                currentPara = maskParas.get(vp.getCurrentItem());
                currentPara.setEasy(true);
                paraBox.put(currentPara);

                if (vp.getCurrentItem() == views.size() - 1) {
                    Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
                } else {
                    vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                }

                dataEntity.setEasyNum(dataEntity.getEasyNum() + 1);

                vp.setTouchCount(0);
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

    @Override
    protected void onStop() {
        super.onStop();
        dataBox.put(dataEntity);
    }
}

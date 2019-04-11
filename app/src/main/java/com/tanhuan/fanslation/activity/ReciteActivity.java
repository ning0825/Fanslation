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

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.ParaEntity;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class ReciteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "----ReciteActivity----";

    int whichBook = 1;

    Box<BookEntity> bookBox;
    BookEntity bookEntity;
    List<ParaEntity> paras;

    List<View> views;

    Toolbar toolbar;
    ViewPager vp;

    MyAdapter myAdapter;

    Button btForget;
    Button btRemember;

    ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);

        btForget = findViewById(R.id.bt_forget);
        btRemember = findViewById(R.id.bt_remember);

        bookBox = BaseApp.getBoxStore().boxFor(BookEntity.class);
        bookEntity = bookBox.get(whichBook);
        paras = bookEntity.toManyTransEntities;

        //initialize toolbar
        toolbar = findViewById(R.id.tb_recite);
        setSupportActionBar(toolbar);
        if ((actionBar = getSupportActionBar()) != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener((v -> onBackPressed()));

        //initialize viewPager
        vp = findViewById(R.id.vp_recite);
        views = new ArrayList<>();
        for (ParaEntity paraEntity : paras) {
            View view = getLayoutInflater().inflate(R.layout.item_vp_recite, null);
            ((TextView) view.findViewById(R.id.tv_recite_para)).setText(paraEntity.getTrans());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float paraY = view.findViewById(R.id.tv_recite_para).getY();
                    TextView tvInput = view.findViewById(R.id.tv_recite_input);
                    TextView tvPara = view.findViewById(R.id.tv_recite_para);
                    ObjectAnimator transInput = ObjectAnimator.ofFloat(tvInput, View.Y, tvInput.getY(), paraY - tvInput.getHeight());
                    ObjectAnimator alphaPara = ObjectAnimator.ofFloat(tvPara, View.ALPHA, 0, 1);
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(transInput, alphaPara);
                    set.start();
//                    transInput.start();
//                    alphaPara.start();
                    Toast.makeText(ReciteActivity.this, "click", Toast.LENGTH_SHORT).show();
                }
            });

            views.add(view);
        }
        myAdapter = new MyAdapter(views);
        vp.setAdapter(myAdapter);
//        vp.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
//

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_forget:
                break;
            case R.id.bt_remember:
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

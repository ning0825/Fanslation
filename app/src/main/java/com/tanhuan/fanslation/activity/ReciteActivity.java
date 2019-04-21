package com.tanhuan.fanslation.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class ReciteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "----ReciteActivity----";

    int whichBook = 1;

    Box<BookEntity> bookBox;
    BookEntity bookEntity;
    List<ParaEntity> initParas;
    List<ParaEntity> maskParas;
    ParaEntity currentPara;

    List<View> views;

    Toolbar toolbar;
    UnrollViewPager vp;
    MyAdapter myAdapter;
    Button btForget;
    Button btRemember;
    ActionBar actionBar;

    Gson gson;

    //rememberCount 小于 level 的单词会显示
    int level = 5;

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
        initParas = bookEntity.toManyTransEntities;
        maskParas = getMaskPara(initParas);


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

            String trans = paraEntity.getTrans();
            ((TextView) view.findViewById(R.id.tv_recite_para)).setText(trans);

            views.add(view);
        }
        myAdapter = new MyAdapter(views);
        vp.setAdapter(myAdapter);
        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TouchTest", "onClick: " );
                View view = views.get(vp.getCurrentItem());
                TextView tvInput = view.findViewById(R.id.tv_recite_input);
                TextView tvPara = view.findViewById(R.id.tv_recite_para);
                float paraY = tvPara.getY();
                ObjectAnimator transInput = ObjectAnimator.ofFloat(tvInput, View.Y, tvInput.getY(), paraY - tvInput.getHeight() - ViewUtil.dp2px(ReciteActivity.this, 20));
                ObjectAnimator alphaPara = ObjectAnimator.ofFloat(tvPara, View.ALPHA, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(transInput, alphaPara);
                set.start();
            }
        });
    }

    private List<ParaEntity> getMaskPara(List<ParaEntity> paraEntities) {
        List<ParaEntity> pes = new ArrayList<>();

        for (ParaEntity para : paraEntities) {
            //todo 思考一番显示哪些单词
            if (para.getRemeberCount() < level) {
                pes.add(para);
            }
        }

        return pes;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_forget:
                currentPara = maskParas.get(vp.getCurrentItem());
                currentPara.setRemeberCount(currentPara.getRemeberCount() - 1);
                break;
            case R.id.bt_remember:
                currentPara = maskParas.get(vp.getCurrentItem());
                currentPara.setRemeberCount(currentPara.getRemeberCount() + 1);

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

package com.tanhuan.fanslation.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.ParaEntity;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class ReciteActivity extends AppCompatActivity {
    private static final String TAG = "----ReciteActivity----";

    int whichBook = 1;

    Box<BookEntity> bookBox;
    BookEntity bookEntity;
    List<ParaEntity> paras;

    Toolbar toolbar;
    ViewPager vp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);

        bookBox = BaseApp.getBoxStore().boxFor(BookEntity.class);
        bookEntity = bookBox.get(whichBook);
        paras = bookEntity.toManyTransEntities;

        toolbar = findViewById(R.id.tb_recite);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener((v -> onBackPressed()));

        vp = findViewById(R.id.vp_recite);
        vp.setAdapter(new MyAdapter(paras));

        
    }

    class MyAdapter extends PagerAdapter {
        List<ParaEntity> paraEntities;

        MyAdapter(List<ParaEntity> paraEntities) {
            this.paraEntities = paraEntities;
        }

        @NonNull
        @Override
        public View instantiateItem(@NonNull ViewGroup container, int position) {
            View v = getLayoutInflater().inflate(R.layout.item_vp_recite, null);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return paraEntities.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        }
    }
}

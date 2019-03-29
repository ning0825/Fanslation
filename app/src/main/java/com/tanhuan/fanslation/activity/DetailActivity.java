package com.tanhuan.fanslation.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.mvp.IView;
import com.tanhuan.fanslation.mvp.ParaPresenter;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements IView<ParaBean> {
    String key;

    TextView tvKey;
    TextView tvPhone;

    ParaPresenter paraPresenter;

    TabLayout tbDetail;
    ViewPager vpDetail;
    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tbDetail = findViewById(R.id.tb_detail);
        vpDetail = findViewById(R.id.vp_detail);

        paraPresenter = new ParaPresenter(this);
        tvKey = findViewById(R.id.tv_key);
        tvPhone = findViewById(R.id.tv_phone);

        Intent intent = getIntent();
        if (intent != null) {
            key = intent.getStringExtra("key");
        }

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
        tvKey.setText(paraBean.getInput());
        tvPhone.setText(paraBean.getEc().getWord().get(0).getUsphone());

        //viewPager init
        Fragment fragment = new StandandFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("para", paraBean);
        fragment.setArguments(bundle);

        fragments = new ArrayList<>();
        fragments.add(fragment);

        if (paraBean.getCollins() != null) {
            Fragment fragment1 = new CollinsFragment();
            fragment1.setArguments(bundle);
            fragments.add(fragment1);
        }

        VpAdater vpAdater = new VpAdater(getSupportFragmentManager());
        vpDetail.setAdapter(vpAdater);
        tbDetail.setupWithViewPager(vpDetail);
    }
}

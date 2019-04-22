package com.tanhuan.fanslation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.customview.StatisticView;

public class StatisticActivity extends AppCompatActivity {
    Toolbar tbStatistic;
    StatisticView statisticView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        tbStatistic = findViewById(R.id.tb_statistic);
        statisticView = findViewById(R.id.sv_statistic);

        setSupportActionBar(tbStatistic);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        statisticView.startAnim();
    }
}

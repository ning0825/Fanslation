package com.tanhuan.fanslation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.customview.StatisticView;

public class StatisticActivity extends AppCompatActivity {
    Toolbar tbStatistic;
    StatisticView statisticView;

    TextView tvNumSearch, tvNumRecite, tvNumEasy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        tbStatistic = findViewById(R.id.tb_statistic);
        statisticView = findViewById(R.id.sv_statistic);
        tvNumSearch = findViewById(R.id.tv_num_search);
        tvNumRecite = findViewById(R.id.tv_num_recite);
        tvNumEasy = findViewById(R.id.tv_num_easy);

        setSupportActionBar(tbStatistic);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.start_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statisticView.startAnim();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

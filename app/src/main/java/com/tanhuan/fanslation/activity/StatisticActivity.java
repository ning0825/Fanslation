package com.tanhuan.fanslation.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.customview.StatisticView;
import com.tanhuan.fanslation.entity.DataEntity;
import com.tanhuan.fanslation.entity.DataEntity_;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.util.ViewUtil;

import java.util.Date;
import java.util.List;

import io.objectbox.Box;

public class StatisticActivity extends AppCompatActivity {
    Toolbar tbStatistic;
    StatisticView statisticView;

    TextView tvNumSearch, tvNumRecite, tvNumEasy;
    //num today
    DataEntity dataToday;
    int searchToday, reciteToday, easyToday;

    //num selected day by click diagram
    DataEntity dataDay;
    int searchDay, reciteDay, easyDay;

    Box<DataEntity> dataBox;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        tbStatistic = findViewById(R.id.tb_statistic);
        statisticView = findViewById(R.id.sv_statistic);
        tvNumSearch = findViewById(R.id.tv_num_search);
        tvNumRecite = findViewById(R.id.tv_num_recite);
        tvNumEasy = findViewById(R.id.tv_num_easy);

        dataBox = BaseApp.getBoxStore().boxFor(DataEntity.class);

        setSupportActionBar(tbStatistic);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<DataEntity> datas = dataBox.query().equal(DataEntity_.date, ViewUtil.getDate()).build().find();
        if (datas.size() != 0) {
            dataToday = datas.get(0);
            searchToday = dataToday.getSearchNum();
            reciteToday = dataToday.getReciteNum();
            easyToday = dataToday.getEasyNum();
        } else {
            searchToday = 0;
            reciteToday = 0;
            easyToday = 0;
        }

        tvNumSearch.setText(searchToday + "");
        tvNumRecite.setText(reciteToday + "");
        tvNumEasy.setText(easyToday + "");

        statisticView.startAnim();
    }
}

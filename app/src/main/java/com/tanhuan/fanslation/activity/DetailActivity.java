package com.tanhuan.fanslation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.mvp.IView;
import com.tanhuan.fanslation.mvp.ParaPresenter;

public class DetailActivity extends AppCompatActivity implements IView<ParaBean> {
    //查询的单词
    String key;

    TextView tvKey;

    ParaPresenter paraPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_detail);

        //进出动画
        getWindow().setEnterTransition(new Slide(Gravity.TOP));

        paraPresenter = new ParaPresenter(this);
        tvKey = findViewById(R.id.tv_key);

        Intent intent = getIntent();
        if (intent != null) {
            key = intent.getStringExtra("key");
        }

        paraPresenter.request(key);
    }

    @Override
    public void showResult(ParaBean paraBean) {
        tvKey.setText(paraBean.toString());
    }
}

package com.tanhuan.fanslation.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.transition.Explode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.AssocBean;
import com.tanhuan.fanslation.mvp.AssocPresenter;
import com.tanhuan.fanslation.mvp.IView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements IView<AssocBean> {
    Button btSubmit;
    EditText etInputSearch;
    ListView lvAssoc;

    AssocPresenter assocPresenter;
    List<String> keyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btSubmit = findViewById(R.id.bt_submit);
        etInputSearch = findViewById(R.id.et_input_search);
        lvAssoc = findViewById(R.id.lv_associate);

        assocPresenter = new AssocPresenter(this);

        keyList = new ArrayList<>();

        etInputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    if (lvAssoc.getVisibility() == View.GONE) {
                        lvAssoc.setVisibility(View.VISIBLE);
                    }
                    assocPresenter.request(s.toString());
                } else {
                    lvAssoc.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        lvAssoc.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            intent.putExtra("key", keyList.get(position));
            startActivity(intent);
        });
    }

    @Override
    public void showResult(AssocBean assocBean) {
        if (assocBean.getResult().getCode() == 200) {
            keyList.clear();
            for (AssocBean.DataBean.EntriesBean entriesBean : assocBean.getData().getEntries()) {
                keyList.add(entriesBean.getEntry());
            }
            Collections.reverse(keyList);
            lvAssoc.setAdapter(new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, keyList));
        }
    }
}

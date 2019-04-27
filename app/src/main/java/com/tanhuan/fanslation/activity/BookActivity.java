package com.tanhuan.fanslation.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.customview.InScrollListView;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.entity.UserEntity;
import com.tanhuan.fanslation.util.Constants;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = "---BookActivity---";

    BoxStore store;
    Box<BookEntity> bookBox;
    Box<ParaEntity> paraBox;
    BookEntity bookEntity;

    long bookIndex;
    InScrollListView lvbook;
    List<ParaEntity> transList;

    TransListAdapter transListAdapter;

    Toolbar toolbar;
    CollapsingToolbarLayout ctbl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        toolbar = findViewById(R.id.tb_book);
        ctbl = findViewById(R.id.ctbl);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        store = (BaseApp.getBoxStore());
        bookBox = store.boxFor(BookEntity.class);
        paraBox = store.boxFor(ParaEntity.class);

        lvbook = findViewById(R.id.lv_book);

        //whick book
        bookIndex = getIntent().getLongExtra("bookId", 1);
        bookEntity = bookBox.get(bookIndex);
        Log.e(TAG, "onCreate: " + bookEntity.bookName );
        getSupportActionBar().setTitle(bookEntity.getBookName());
        transList = new ArrayList<>();

        transList = bookEntity.toManyTransEntities;

        Log.e(TAG, "translist size: " + transList.size() );

        transListAdapter = new TransListAdapter(this, R.layout.item_book_list, transList);
        lvbook.setAdapter(transListAdapter);
        lvbook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemSelected: " + position );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvbook.setOnItemClickListener(new ToDetailItemClickListener());
        lvbook.setMultiChoiceModeListener(new ModeCallBack());
    }

    //正常模式下的item点击事件
    class ToDetailItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(BookActivity.this, DetailActivity.class);
            intent.putExtra("key", transList.get(position).getInput());
            startActivity(intent);
        }
    }

    //选择模式下的item点击事件
    class CheckItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            transListAdapter.notifyDataSetChanged();
        }
    }

    public class TransListAdapter extends ArrayAdapter {
        int resourceId;
        List<ParaEntity> transEntities;

        TransListAdapter(Context context, int resource, List<ParaEntity> objects) {
            super(context, resource, objects);
            transEntities = objects;
            resourceId = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
                viewHolder = new ViewHolder();

                viewHolder.tvListWordName = convertView.findViewById(R.id.tv_list_word_name);
                viewHolder.tvListPara = convertView.findViewById(R.id.tv_list_para);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvListWordName.setText(transEntities.get(position).getInput());
            viewHolder.tvListPara.setText(transEntities.get(position).getTrans());

            if (lvbook.isItemChecked(position)) {
                convertView.setBackgroundColor(Color.YELLOW);
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }

            return convertView;
        }

        @Override
        public int getCount() {
            return transEntities.size();
        }

        @Override
        public ParaEntity getItem(int position) {
            return transEntities.get(position);
        }

        public class ViewHolder {
            TextView tvListWordName;
            TextView tvListPara;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_book:
                lvbook.setOnItemClickListener(new CheckItemClickListener());
                lvbook.setItemChecked(0, true);
                lvbook.clearChoices();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void delTrans() {
        List<ParaEntity> transToDel = new ArrayList<>();
        for (int i = 0; i < transList.size(); i++) {
            if (lvbook.getCheckedItemPositions().get(i)) {
                transToDel.add(transList.get(i));
            }
        }
        paraBox.remove(transToDel);

        bookIndex = getIntent().getIntExtra("bookId", 1);
        bookEntity = bookBox.get(bookIndex);
        transList = new ArrayList<>();

        transList = bookEntity.toManyTransEntities;
        transListAdapter = new TransListAdapter(this, R.layout.item_book_list, transList);
        lvbook.setAdapter(transListAdapter);
        lvbook.setOnItemClickListener(new ToDetailItemClickListener());
    }

    class ModeCallBack implements AbsListView.MultiChoiceModeListener {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.menu_choice, menu);
            ctbl.setVisibility(View.GONE);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()) {
                case R.id.choose_all:
                    for (int i = 0; i < lvbook.getCount(); i++) {
                        lvbook.setItemChecked(i, true);
                    }
                    transListAdapter.notifyDataSetChanged();
                    break;
                case R.id.delete:
                    delTrans();
                    mode.finish();
                    break;
            }
            return true;
        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            mode.invalidate();
            transListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            ctbl.setVisibility(View.VISIBLE);
        }
    }
}

package com.tanhuan.fanslation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tanhuan.fanslation.MtoCView;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.util.ViewUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "----MainActivity----";

    EditText etInput;

    MyClickListener myClickListener;

    FrameLayout flMenu;
    ListView lvMenu;
    ConstraintLayout clDialog;
    View bgMenu;
    MtoCView btMenu;

    ValueAnimator flAnimator;
    ObjectAnimator bgMenuAnimator;
    ObjectAnimator clMenuAnimator;

    int ANIM_DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        flMenu = findViewById(R.id.fl_menu);
        clDialog = findViewById(R.id.cl_dialog);
        lvMenu = clDialog.findViewById(R.id.lv_more);
        bgMenu = findViewById(R.id.bg_meau);
        btMenu = findViewById(R.id.bt_menu);

        String[] menus = {"test1", "test2", "test3", "test4"};
        MoreAdapter moreAdapter = new MoreAdapter(this, R.layout.item_more, menus);
        lvMenu.setAdapter(moreAdapter);

        myClickListener = new MyClickListener();
        etInput.setOnClickListener(myClickListener);
        bgMenu.setOnClickListener(myClickListener);
        btMenu.setOnClickListener(myClickListener);

        animatorInit();
    }

    private void animatorInit() {

        flAnimator = ValueAnimator.ofFloat(ViewUtil.dp2px(this, 60), 1000);
        bgMenuAnimator = ObjectAnimator.ofFloat(bgMenu, View.ALPHA, 0, 0.3f);
        clMenuAnimator = ObjectAnimator.ofFloat(clDialog, View.ALPHA, 0,1);

        flAnimator.setDuration(ANIM_DURATION);
        bgMenuAnimator.setDuration(ANIM_DURATION);
        clMenuAnimator.setDuration(ANIM_DURATION);

        flAnimator.setInterpolator(new AccelerateDecelerateInterpolator());


        bgMenuAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                if (isReverse) {
                    bgMenu.setClickable(false);
                } else {
                    bgMenu.setVisibility(View.VISIBLE);
                    flMenu.setClickable(false);
                    bgMenu.setClickable(false);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if (isReverse) {
                    bgMenu.setVisibility(View.GONE);
                    flMenu.setClickable(true);
                    bgMenu.setClickable(true);
                } else {
                    bgMenu.setClickable(true);
                }
            }
        });

        ViewGroup.LayoutParams params = flMenu.getLayoutParams();

        flAnimator.addUpdateListener((animation -> {
            params.width = ((Number) animation.getAnimatedValue()).intValue();
            params.height = ((Number) animation.getAnimatedValue()).intValue();
            flMenu.setLayoutParams(params);
        }));
    }

    /*
    * Click listener*/
    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_menu:
                    Log.e("btmenu", "onClick: " );
                    if (!btMenu.checked) {
                        bgMenuAnimator.reverse();
                        flAnimator.reverse();
                        clMenuAnimator.reverse();
                    } else {
                        flAnimator.start();
                        bgMenuAnimator.start();
                        clMenuAnimator.start();
                    }
                    break;
                case R.id.et_input:
                    startActivity(new Intent(MainActivity.this, SearchActivity.class ));
                    AnimationSet animation = (AnimationSet) AnimationUtils.loadAnimation(MainActivity.this, R.anim.input_transtion);
                    etInput.startAnimation(animation);
                    break;
                case R.id.bg_meau:
                    bgMenuAnimator.reverse();
                    flAnimator.reverse();
                    btMenu.setChecked(false);
                    clMenuAnimator.reverse();
                    break;
                default:
                    break;
            }
        }
    }

    /*
    * Menu list adapter*/
    static class MoreAdapter extends ArrayAdapter {
        int resourceId;
        String[] moreOp;

        MoreAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            moreOp = objects;
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
                viewHolder = new ViewHolder();

                viewHolder.tvMoreOp = convertView.findViewById(R.id.tv_more_op);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvMoreOp.setText(moreOp[position]);

            return convertView;
        }

        @Override
        public int getCount() {
            return moreOp.length;
        }

        @Override
        public String getItem(int position) {
            return moreOp[position];
        }

        private class ViewHolder {
            TextView tvMoreOp;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.tanhuan.fanslation.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tanhuan.fanslation.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "----MainActivity----";

    EditText etInput;

    MyClickListener myClickListener;

    FrameLayout flMenu;
    ListView lvMenu;
    ConstraintLayout clDialog;
    View bgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        flMenu = findViewById(R.id.fl_menu);
        clDialog = findViewById(R.id.cl_dialog);
        lvMenu = clDialog.findViewById(R.id.lv_more);
        bgMenu = findViewById(R.id.bg_meau);

        String[] menus = {"test1", "test2", "test3", "test4"};
        lvMenu.setAdapter(new MoreAdapter(this, R.layout.item_more, menus));

        myClickListener = new MyClickListener();
        etInput.setOnClickListener(myClickListener);
        flMenu.setOnClickListener(myClickListener);
        bgMenu.setOnClickListener(myClickListener);

        clDialog.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e(TAG, "onGlobalLayout: " + clDialog.getHeight());
            }
        });
    }

    /*
    * Click listener*/
    class MyClickListener implements View.OnClickListener {
        ValueAnimator animator = ValueAnimator.ofFloat(200, 1000);
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fl_menu:
                    //show menu background
                    bgMenu.setVisibility(View.VISIBLE);
                    bgMenu.animate().alpha(0.3f).setDuration(500).start();
                    //show menu
                    animator.removeAllListeners();
                    ViewGroup.LayoutParams params = flMenu.getLayoutParams();
                    animator.addUpdateListener((animation -> {
                        Log.e(TAG, "onAnimationUpdate: " + animation.getAnimatedValue() );
                        params.width = ((Number) animation.getAnimatedValue()).intValue();
                        params.height = ((Number) animation.getAnimatedValue()).intValue();
                        flMenu.setLayoutParams(params);
                    }));
                    animator.setDuration(300);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.start();
                    break;
                case R.id.et_input:
                    startActivity(new Intent(MainActivity.this, SearchActivity.class ));
                    AnimationSet animation = (AnimationSet) AnimationUtils.loadAnimation(MainActivity.this, R.anim.input_transtion);
                    etInput.startAnimation(animation);
                    break;
                case R.id.bg_meau:
                    //show menu background
                    bgMenu.animate().alpha(0).setDuration(500).start();
                    //show menu
                    animator.reverse();
                    break;
                default:
                    break;
            }
        }
    }

    /*
    * Menu list adapter*/
    class MoreAdapter extends ArrayAdapter {
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

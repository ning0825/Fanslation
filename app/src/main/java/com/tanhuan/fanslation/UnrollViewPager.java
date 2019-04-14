package com.tanhuan.fanslation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class UnrollViewPager extends ViewPager {
    private static final String TAG = "touchtest";
    public UnrollViewPager(@NonNull Context context) {
        super(context);
    }

    public UnrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*
    * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: " );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent: " );
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onTouchEvent: ");
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            performClick();
        }
        return true;
    }
}

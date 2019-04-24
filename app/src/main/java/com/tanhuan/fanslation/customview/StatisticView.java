package com.tanhuan.fanslation.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

public class StatisticView extends View {
    private static final String TAG = "StatisticView";

    Paint linePaint;
    Paint redPaint;
    Paint greenPaint;
    Paint yellowPaint;

    int width;
    int height;
    float sheetPadding = 80;
    int overAxis = 0;

    //data
    String[] dates = new String[7];
    int[] searchNums = new int[7];
    float[] reciteNums = new float[7];
    int[] easyNums = new int[7];
    int[] ress;

    float maxRecite;

    //the path to draw recite lines
    Path recitePath;
    //the path of xy axis
    Path axisPath;
    //dots on x axis
    float[] xs = new float[7];
    float[] ys = new float[7];

    //mark on y axis
    List<Float> markYs = new ArrayList<>();


    //length per date
    float xUnitLength;
    //length per reciteNum
    float yUnitLength;
    //length per 10 reciteNum
    float yMarkLength;

    //date text width
    float dateWidth;

    boolean t = true;

    ValueAnimator animator;


    {

    }

//    public StatisticView(Context context) {
//        super(context);
//    }

    public StatisticView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        linePaint = new Paint();
        linePaint.setColor(Color.GRAY);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(10);

        redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPaint.setStrokeWidth(20);
        redPaint.setStrokeCap(Paint.Cap.BUTT);

        greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        greenPaint.setStrokeWidth(10);

        yellowPaint = new Paint();
        yellowPaint.setColor(Color.GRAY);
        yellowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        greenPaint.setStrokeWidth(3);
        yellowPaint.setTextSize(40);
        //test data
        setDates(new String[]{"16", "17", "18", "19", "20", "21", "22"});
        setReciteNums(new float[]{20, 10, 20, 45, 2, 19, 20});
        ress = new int[]{20, 10, 20, 45, 2, 19, 20};

        //find max num in reciteNums to determine Y
        maxRecite = findMaxRecite(reciteNums);

        recitePath = new Path();
        axisPath = new Path();

        animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                for (int i = 0; i < ress.length; i++) {
//                    reciteNums[i] = (int)(ress[i] * (float)animation.getAnimatedValue());
//                }
                Log.e(TAG, "paix******" + ress[0]);
//                reciteNums[0] = 50;
                invalidate();
                Log.e(TAG, "paix>>>>>>>" + reciteNums[0]);

                for (int i = 0; i < reciteNums.length; i++) {
                    reciteNums[i] = ress[i] * (float)animation.getAnimatedValue();
                }
//                reciteNums[0] = ress[0] * (float)animation.getAnimatedValue();
//                reciteNums[1] = ress[1] * (float)animation.getAnimatedValue();
//                reciteNums[2] = ress[2] * (float)animation.getAnimatedValue();
//                reciteNums[3] = ress[3] * (float)animation.getAnimatedValue();
//                reciteNums[4] = ress[4] * (float)animation.getAnimatedValue();
//                reciteNums[5] = ress[5] * (float)animation.getAnimatedValue();
//                reciteNums[6] = ress[6] * (float)animation.getAnimatedValue();

            }
        });
    }

//    public StatisticView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: ");

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        xUnitLength = (width - sheetPadding * 2 - overAxis) / 8;
        yUnitLength = (height - sheetPadding * 2 - overAxis) / ((maxRecite / 10.0f + 1) * 10);
        yMarkLength = (height - sheetPadding * 2 - overAxis) / (maxRecite / 10.0f + 1);

        dateWidth = yellowPaint.measureText("22");


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (t) {
            for (int i = 0; i < maxRecite / 10; i++) {
                markYs.add(height - sheetPadding - yMarkLength * (i + 1));
            }
            Log.e(TAG, "onDraw: " + "narkYs.size: " + markYs.size() + "maxRecite: " + maxRecite);
            t = false;
        }

        for (int i = 0; i < dates.length; i++) {
            xs[i] = sheetPadding + (i + 1) * xUnitLength;
            ys[i] = reciteNums[i] * yUnitLength;
        }

        //draw reciteNums
        recitePath.reset();
        for (int i = 0; i < dates.length; i++) {
            recitePath.moveTo(xs[i], height - sheetPadding);
            recitePath.rLineTo(0, -ys[i]);
//            Log.e(TAG, "onDraw: ys0:" + ys[0] );
            //draw date
            canvas.drawText(dates[i], xs[i] - dateWidth / 2, height - sheetPadding + dateWidth, yellowPaint);
        }
//        Log.e(TAG, "onDraw: ys0:" + ys[0]);
        canvas.drawPath(recitePath, redPaint);

        //draw x and y axis
        axisPath.moveTo(sheetPadding, sheetPadding);
        axisPath.lineTo(sheetPadding, height - sheetPadding);
        axisPath.lineTo(width - sheetPadding, height - sheetPadding);
        canvas.drawPath(axisPath, linePaint);

        //draw mark on Y axis
        for (int i = 0; i < markYs.size(); i++) {
            canvas.drawLine(sheetPadding, markYs.get(i), sheetPadding + 10, markYs.get(i), linePaint);
            canvas.drawText(10 * (i + 1) + "", sheetPadding - dateWidth - 10, markYs.get(i), yellowPaint);
        }
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }

    public void setSearchNums(int[] searchNums) {
        this.searchNums = searchNums;
    }

    public void setReciteNums(float[] reciteNums1) {
        this.reciteNums = reciteNums1;
    }

    public void setEasyNums(int[] easyNums) {
        this.easyNums = easyNums;
    }

    private float findMaxRecite(float[] reciteArray) {
        float max = 0;
        for (float i : reciteArray) {
            if (i > max) {
                max = i;
            }
        }
        Log.e(TAG, "findMaxRecite: " + max);
        return max;
    }

    public void startAnim() {

        animator.start();
    }
}

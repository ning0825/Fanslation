package com.tanhuan.fanslation.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class StatisticView extends View {
    Paint linePaint;
    Paint redPaint;
    Paint greenPaint;
    Paint yellowPaint;

    int width;
    int height;
    float sheetPadding = 50 ;

    {
        linePaint = new Paint();
        linePaint.setColor(Color.GRAY);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setStrokeWidth(10);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setStrokeWidth(10);

        greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setStrokeWidth(10);

        yellowPaint = new Paint();
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setStrokeWidth(10);
    }

    public StatisticView(Context context) {
        super(context);
    }

    public StatisticView(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatisticView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //y axis
        canvas.drawLine(sheetPadding, 0, sheetPadding, height-sheetPadding, linePaint);
        //x axis
        canvas.drawLine(sheetPadding, height-sheetPadding, width-sheetPadding, height-sheetPadding, linePaint);

    }
}

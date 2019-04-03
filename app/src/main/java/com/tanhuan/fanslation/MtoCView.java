package com.tanhuan.fanslation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MtoCView extends View {
    private static final String TAG = ">>>>>>>>>>>>>>>>>>>>>";

    Paint paint;

    int width;
    int height;

    float[] topLine = new float[4];
    float[] bottomLine = new float[4];

    public boolean checked = false;

    ValueAnimator valueAnimator;

    {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public MtoCView(Context context) {
        super(context);
    }

    public MtoCView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MtoCView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        topLine[0] = width / 4;
        topLine[1] = height / 3;
        topLine[2] = width / 4 * 3;
        topLine[3] = height / 3;

        bottomLine[0] = width / 4;
        bottomLine[1] = height / 3 * 2;
        bottomLine[2] = width / 4 * 3;
        bottomLine[3] = height / 3 * 2;

        valueAnimator = ValueAnimator.ofFloat(height / 3, height / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        canvas.drawLines(topLine, paint);
        canvas.drawLines(bottomLine, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            toggle();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            performClick();
        }
        return true;
    }

    public void toggle() {
        if (!checked) {
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float f = (float) animation.getAnimatedValue();
                    int cha = height / 2 - height / 3;
                    //translationY 每变化一个单位，线条旋转的角度
                    float per = (float) (Math.PI / 4 / cha);
                    float per2 = (f - height / 3) * per;

                    topLine[0] = (float) (width / 2 - width / 4 * Math.cos(per2));
                    topLine[1] = (float) (f - width / 4 * Math.sin(per2));
                    topLine[2] = (float) (width / 2 + width / 4 * Math.cos(per2));
                    topLine[3] = (float) (f + width / 4 * Math.sin(per2));

                    bottomLine[0] = topLine[0];
                    bottomLine[1] = height - topLine[1];
                    bottomLine[2] = topLine[2];
                    bottomLine[3] = height - topLine[3];

                    postInvalidate();
                    Log.e(TAG, "onAnimationUpdate: "+ f );
                }
            });
            valueAnimator.setDuration(500);
            valueAnimator.start();
            checked = true;
        } else {
            valueAnimator.reverse();
            checked = false;
        }
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        valueAnimator.reverse();
    }
}

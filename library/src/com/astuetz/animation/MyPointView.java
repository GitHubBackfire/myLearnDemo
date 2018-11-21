package com.astuetz.animation;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class MyPointView extends View {
    private MyPoint mCurPoint;
    private Paint mPaint;

    public MyPointView(Context context) {
        super(context);
    }

    public MyPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurPoint != null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300, 300, mCurPoint.getRadius(), mPaint);
        }
    }

    public void doPointAnim() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new MyPoint(20), new MyPoint(200));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurPoint = (MyPoint) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }


    class MyPoint {
        private int radius;

        public MyPoint(int radius) {
            this.radius = radius;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

    }

    class PointEvaluator implements TypeEvaluator<MyPoint> {

        @Override
        public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {
            int start = startValue.getRadius();
            int end = endValue.getRadius();
            int curValue = (int) (start + fraction * (end - start));
            return new MyPoint(curValue);
        }
    }


}

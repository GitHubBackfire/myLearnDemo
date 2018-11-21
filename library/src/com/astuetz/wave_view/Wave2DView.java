package com.astuetz.wave_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Wave2DView extends View {

    /**
     * 绘制最中心圆的Paint
     */
    private Paint mInnerCirclePaint;

    /**
     * 绘制外部圆环的Paint
     */
    private Paint mOutterRingPaint;

    /**
     * View宽度
     */
    private int mWidth;

    /**
     * View高度
     */
    private int mHeight;

    /**
     * 外环宽度
     */
    private int mOuttterRingWidth = 50;

    /**
     * 内圆半径
     */
    private int mInnerRadius = 50;

    /**
     * 存储计算所得的外环半径
     */
    private int[] mRadius = new int[4];

    /**
     * 控制外环个数变化的属性动画对象
     */
    private ValueAnimator mValueAnimator;

    /**
     * 绘制的外环总个数
     */
    private int mOutterRingCount = 4;

    public void startAnimation() {
        //创建ValueAnimator对象，按照整型值从0变化到4
        mValueAnimator = ValueAnimator.ofInt(0, 9);
        //设置动画重复类型,RESTART--重新开始，REVERSE--值反转
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        //设置动画重复次数，-1--不限制次数
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.setDuration(2000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOutterRingCount = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mValueAnimator.start();
    }

    public Wave2DView(Context context) {
        super(context);
        init();
    }

    public Wave2DView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Wave2DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setColor(Color.BLUE);
        /** 内部画笔为实心 **/
        mInnerCirclePaint.setStyle(Paint.Style.FILL);

        mOutterRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutterRingPaint.setColor(Color.BLUE);
        /** 外部画笔为空心 **/
        mOutterRingPaint.setStyle(Paint.Style.STROKE);
        mOutterRingPaint.setStrokeWidth(mOuttterRingWidth);
    }

    //外圆半径
    private void caculateRadius() {
        for (int i = 0; i < 4; i++) {
            mRadius[i] = mInnerRadius + (i * 2 + 1) * mOuttterRingWidth;
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            mWidth = w;
            mHeight = h;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        caculateRadius();
        canvas.drawCircle(mWidth / 2, mHeight / 2, mInnerRadius, mInnerCirclePaint);
        for (int i = 0; i < 4; i++) {
            /** 改变外环的颜色透明度 **/
            mOutterRingPaint.setAlpha(255 - (int) (255 * ((float) (i + 1) / 5)));
            canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius[i], mOutterRingPaint);
        }
    }
}

package com.astuetz.canvas_action;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 位移、缩放
 */
public class CanvasTranslate extends View {
    private int mWidth;
    private int mHeight;

    public CanvasTranslate(Context context) {
        super(context);
    }

    public CanvasTranslate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasTranslate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.translate(30, 30);
        canvas.drawCircle(0, 0, 20, paint);

        // 在坐标原点绘制一个蓝色圆形
        paint.setColor(Color.BLUE);
        canvas.translate(20, 20);
        canvas.drawCircle(0, 0, 10, paint);

        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域
        for (int i = 0; i <= 10; i++) {
            paint.setStyle(Paint.Style.STROKE);
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rect, paint);
        }
    }
}

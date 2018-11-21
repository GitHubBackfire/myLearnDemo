package com.astuetz.canvas_action;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.astuetz.utils.SizeUtils;

import java.util.ArrayList;

public class BasicBarView extends View {
    private int MINI_BAR_WIDTH;
    private int BAR_SIDE_MARGIN;
    private int TEXT_TOP_MARGIN;
    private final int TEXT_COLOR = Color.parseColor("#9B9A9B");
    private final int BACKGROUND_COLOR = Color.parseColor("#F6F6F6");
    private final int FOREGROUND_COLOR = Color.parseColor("#FC496D");

    private ArrayList<Float> targetPercentList;


    private Paint textPaint;
    private Paint bgPaint;
    private Paint fgPaint;
    private Rect rect;
    private int barWidth;
    private int bottomTextDescent;
    private int topMargin;


    public BasicBarView(Context context) {
        super(context);
        init();
    }

    public BasicBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BasicBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mViewWidth = measureWidth(widthMeasureSpec);
        int mViewHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(mViewWidth, heightMeasureSpec);
    }

    private int measureWidth(int measureSpec) {
        int preferred = 0;
        if (targetPercentList != null) {
            preferred = targetPercentList.size() * (barWidth + BAR_SIDE_MARGIN);
        }
        return getMeasurement(measureSpec, preferred);
    }

    private int measureHeight(int measureSpec) {
        int preferred = 222;
        return getMeasurement(measureSpec, preferred);
    }

    private int getMeasurement(int measureSpec, int preferred) {
        int specSize = MeasureSpec.getSize(measureSpec);
        int measurement;
        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.EXACTLY:
                measurement = specSize;
                break;
            case MeasureSpec.AT_MOST:
                measurement = Math.min(preferred, specSize);
                break;
            default:
                measurement = preferred;
                break;
        }
        return measurement;
    }

    private void init() {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(BACKGROUND_COLOR);
        fgPaint = new Paint(bgPaint);
        fgPaint.setColor(FOREGROUND_COLOR);
        rect = new Rect();
        topMargin = SizeUtils.dip2px(5);
        int textSize = SizeUtils.sp2px(15);
        barWidth = SizeUtils.dip2px(22);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        MINI_BAR_WIDTH = SizeUtils.dip2px(22);
        BAR_SIDE_MARGIN = SizeUtils.dip2px(22);
        TEXT_TOP_MARGIN = SizeUtils.dip2px(5);
    }

    public void setDataList(ArrayList<Integer> list, int max) {
        targetPercentList = new ArrayList<Float>();
        if (max == 0) max = 1;

        for (Integer integer : list) {
            targetPercentList.add(1 - (float) integer / (float) max);
        }
        invalidate();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 1;
        if (targetPercentList != null && !targetPercentList.isEmpty()) {
            for (Float f : targetPercentList) {
                rect.set(BAR_SIDE_MARGIN * i + barWidth * (i - 1), topMargin,
                        (BAR_SIDE_MARGIN + barWidth) * i,
                        getHeight() - TEXT_TOP_MARGIN);
                canvas.drawRect(rect, bgPaint);
                /*rect.set(BAR_SIDE_MARGIN*i+barWidth*(i-1),
                        topMargin+(int)((getHeight()-topMargin)*percentList.get(i-1)),
                        (BAR_SIDE_MARGIN+barWidth)* i,
                        getHeight()-bottomTextHeight-TEXT_TOP_MARGIN);*/
                /**
                 * The correct total height is "getHeight()-topMargin-bottomTextHeight-TEXT_TOP_MARGIN",not "getHeight()-topMargin".
                 */
                rect.set(BAR_SIDE_MARGIN * i + barWidth * (i - 1), topMargin + (int) ((getHeight()
                                - topMargin
                                - TEXT_TOP_MARGIN) * targetPercentList.get(i - 1)),
                        (BAR_SIDE_MARGIN + barWidth) * i,
                        getHeight() - TEXT_TOP_MARGIN);
                canvas.drawRect(rect, fgPaint);
                i++;
            }
        }


    }
}

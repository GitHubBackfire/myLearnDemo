package com.astuetz.utils;

import android.content.Context;

public class SizeUtils {
    private static Context mContext;
    public static void init(Context context) {
        mContext = context;
    }

    public static int dip2px(int dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * px转换sp
     */
    public static int px2sp(int pxValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp转换px
     */
    public static int sp2px(int spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);

    }
}


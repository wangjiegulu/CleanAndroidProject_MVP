package com.wangjiegulu.capmvp.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.wangjiegulu.capmvp.application.CAPApplication;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/08/2017.
 */
public final class ResUtil {
    private static final String TAG = ResUtil.class.getSimpleName();

    private ResUtil() {
    }

    public static int dip2px(float dpValue) {
        try {
            final float scale = CAPApplication.instance.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return 0;
    }

    public static int sp2px(float spValue) {
        try {
            float fontScale = CAPApplication.instance.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5f);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return 0;
    }

    public static int px2dip(float pxValue) {
        try {
            float scale = CAPApplication.instance.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return 0;
    }

    public static String getString(int resId) {
        try {
            return CAPApplication.instance.getString(resId);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return "";
    }

    public static String getString(int resId, Object... formatArgs) {
        try {
            return CAPApplication.instance.getString(resId, formatArgs);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return "";
    }

    public static String[] getArrayString(int resId) {
        try {
            return CAPApplication.instance.getResources().getStringArray(resId);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return new String[0];
    }

    public static int getColor(int resId) {
        try {
            return CAPApplication.instance.getResources().getColor(resId);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return 0;
    }

    public static float getDimension(int resId) {
        try {
            return CAPApplication.instance.getResources().getDimension(resId);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return 0;
    }

    public static float getDimensionPixelSize(int resId) {
        try {
            return CAPApplication.instance.getResources().getDimensionPixelSize(resId);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return 0;
    }

    public static Drawable getDrawable(int resId) {
        try {
            return ContextCompat.getDrawable(CAPApplication.instance, resId);
        } catch (Throwable throwable) {
            Log.e(TAG, "", throwable);
        }
        return new ColorDrawable(Color.TRANSPARENT);
    }


}

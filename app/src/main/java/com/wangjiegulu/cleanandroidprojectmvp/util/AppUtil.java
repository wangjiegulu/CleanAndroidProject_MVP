package com.wangjiegulu.cleanandroidprojectmvp.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 02/08/2017.
 */
public final class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();

    private AppUtil() {
    }

    /**
     * is main process
     */
    public static boolean isMainProcess(Context context) {
        try {
            int pid = android.os.Process.myPid();
            String processName = "";
            ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (null == mActivityManager) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    processName = appProcess.processName;
                    break;
                }
            }
            String packageName = context.getPackageName();
            return packageName.equals(processName);
        } catch (Throwable throwable) {
            Log.e(TAG, "isMainProcess() error", throwable);
        }
        return false;
    }

}

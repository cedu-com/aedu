package net.chinaedu.aedu.utils;

import android.app.Application;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class AeduToastUtil {
    private static Context sContext;

    public static void init(Application app) {
        AeduToastUtil.sContext = app.getApplicationContext();
    }

    public static void show(@StringRes int msgResId) {
        if (null == sContext) {
            throw new IllegalStateException("AeduToastUtil not inited");
        }
        Toast.makeText(sContext, msgResId, Toast.LENGTH_SHORT).show();
    }

    public static void show(String msg) {
        if (null == sContext) {
            throw new IllegalStateException("AeduToastUtil not inited");
        }
        Toast.makeText(sContext, msg, Toast.LENGTH_SHORT).show();
    }
}

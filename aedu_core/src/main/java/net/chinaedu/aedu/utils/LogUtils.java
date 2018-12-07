package net.chinaedu.aedu.utils;

import android.util.Log;

/**
 * Created by MartinKent on 2016/1/16.
 */
public class LogUtils {
    private static final int TARGET_TRACE_LEVEL = 5;

    private static boolean debugEnabled = false;

    private static String defaultTag = LogUtils.class.getSimpleName();

    public static boolean isDebugEnabled() {
        return debugEnabled;
    }

    public static void setDebugEnabled(boolean enabled) {
        LogUtils.debugEnabled = enabled;
    }

    public static String getTag() {
        return defaultTag;
    }

    public static void setDefaultTag(String defaultTag) {
        LogUtils.defaultTag = defaultTag;
    }

    public static void v(String msg) {
        log(Log.VERBOSE, defaultTag, msg);
    }

    public static void d(String msg) {
        log(Log.DEBUG, defaultTag, msg);
    }

    public static void e(String msg) {
        log(Log.ERROR, defaultTag, msg);
    }

    public static void i(String msg) {
        log(Log.INFO, defaultTag, msg);
    }

    public static void w(String msg) {
        log(Log.WARN, defaultTag, msg);
    }

    public static void v(String tag, String msg) {
        log(Log.VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        log(Log.DEBUG, tag, msg);
    }

    public static void e(String tag, String msg) {
        log(Log.ERROR, tag, msg);
    }

    public static void i(String tag, String msg) {
        log(Log.INFO, tag, msg);
    }

    public static void w(String tag, String msg) {
        log(Log.WARN, tag, msg);
    }

    /**
     * 根据传入的Log级别打印对应的log
     *
     * @param level   Log级别
     * @param tag     tag
     * @param message log信息
     */
    public static void log(int level, String tag, String message) {
        if (!debugEnabled) {
            return;
        }
        message = appendSuffix(message);
        switch (level) {
            case Log.DEBUG:
                Log.d(tag, message);
                break;
            case Log.ERROR:
                Log.e(tag, message);
                break;
            case Log.INFO:
                Log.i(tag, message);
                break;
            case Log.VERBOSE:
                Log.v(tag, message);
                break;
            case Log.WARN:
                Log.w(tag, message);
                break;
        }
    }

    /**
     * 在Log消息后边追加[文件,类,方法,行]信息
     *
     * @param msg 要输出的log信息
     * @return 在log信息后边追加了[文件, 类, 方法, 行]信息的字符串
     */
    private static String appendSuffix(String msg) {
        StackTraceElement e = Thread.currentThread().getStackTrace()[TARGET_TRACE_LEVEL];
        return msg + "[" + e.getFileName() + "," + e.getClassName().substring(e.getClassName().lastIndexOf(".") + 1) + "," + e.getMethodName() + "," + e.getLineNumber() + "]";
    }

    /**
     * 打印调用堆栈，仅供调试用
     *
     * @param prefix log信息前缀
     */
    public static void printStackTrace(String prefix) {
        prefix = null == prefix ? defaultTag : prefix;
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            StackTraceElement e = elements[i];
            Log.d(prefix, " FILE:" + e.getFileName() + ", CLASS:" + e.getClassName().substring(e.getClassName().lastIndexOf(".") + 1) + ", METHOD:" + e.getMethodName() + ", LINE" + e.getLineNumber());
        }
    }
}

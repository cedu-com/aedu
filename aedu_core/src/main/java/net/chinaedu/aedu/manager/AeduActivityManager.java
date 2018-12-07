package net.chinaedu.aedu.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by qinyun on 2015/12/24.
 * 管理程序中的Activity
 */
@SuppressLint("StaticFieldLeak")
public final class AeduActivityManager implements Application.ActivityLifecycleCallbacks {
    private static AeduActivityManager instance;

    private final List<Activity> mActivityList = new ArrayList<>();
    private Activity mCurrentActivity;

    private AeduActivityManager() {

    }

    public synchronized static AeduActivityManager getInstance() {
        if (instance == null) {
            synchronized (AeduActivityManager.class) {
                if (instance == null) {
                    instance = new AeduActivityManager();
                }
            }
        }
        return instance;
    }

    public synchronized boolean exists(Activity activity) {
        return mActivityList.contains(activity);
    }

    public synchronized void finishAllActivity() {
        while (mActivityList.size() > 0) {
            Activity activity = mActivityList.remove(0);
            activity.finish();
        }
    }

    public synchronized Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (null != activity.getParent()) {
            return;
        }
        mActivityList.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (null != activity.getParent()) {
            return;
        }
        mCurrentActivity = activity;
        if (mActivityList.contains(activity) && !activity.equals(mActivityList.get(mActivityList.size() - 1))) {
            mActivityList.remove(activity);
            mActivityList.add(activity);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (null != activity.getParent()) {
            return;
        }
        if (activity.equals(mCurrentActivity)) {
            mCurrentActivity = null;
        }
        mActivityList.remove(activity);
    }
}

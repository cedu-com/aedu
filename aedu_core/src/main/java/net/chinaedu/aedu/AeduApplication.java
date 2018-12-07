package net.chinaedu.aedu;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.support.annotation.CallSuper;
import android.support.multidex.MultiDex;

import net.chinaedu.aedu.manager.AeduActivityManager;
import net.chinaedu.aedu.utils.AeduToastUtil;

import java.util.List;

/**
 * Created by Qinyun on 2017/2/22.
 */

public abstract class AeduApplication extends Application {

    @CallSuper
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        AeduToastUtil.init(this);
        registerActivityLifecycleCallbacks(AeduActivityManager.getInstance());

        String processName = getCurrentProcessName(getApplicationContext());
        if (getPackageName().equals(processName)) {
            onCreateMainProcess();
        } else {
            onCreateOtherProcess(Process.myPid(), processName);
        }
    }

    protected abstract void onCreateMainProcess();

    protected abstract void onCreateOtherProcess(int pid, String processName);

    private String getCurrentProcessName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == Process.myPid()) {
                return procInfo.processName;
            }
        }
        return null;
    }
}

package net.chinaedu.aedu.sample;

import net.chinaedu.aedu.AeduApplication;
import net.chinaedu.aedu.utils.LogUtils;

/**
 * Created by qinyun on 2017/2/9.
 */

public class FrameworkApplication extends AeduApplication {

    private static FrameworkApplication sInstance;

    public static synchronized FrameworkApplication getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        LogUtils.setDebugEnabled(true);
    }

    @Override
    protected void onCreateMainProcess() {

    }

    @Override
    protected void onCreateOtherProcess(int pid, String processName) {

    }
}

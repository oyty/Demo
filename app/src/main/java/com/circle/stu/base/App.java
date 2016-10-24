package com.circle.stu.base;

import android.app.Activity;
import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oyty on 10/10/16.
 */

public class App extends Application {

    private static App instance;
    private Set<Activity> allActivities;

    public static synchronized App getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        instance = this;
        AVOSCloud.initialize(this, "Lz2OTyScY2zOi0B6P0jHV339-gzGzoHsz", "Kh0gtBqylMVsA8z6lh2Lylsd");

    }

    public void addActivity(Activity activity) {
        if(allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if(allActivities != null) {
            allActivities.remove(activity);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}

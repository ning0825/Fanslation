package com.tanhuan.fanslation;

import android.app.Application;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.tanhuan.fanslation.entity.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class BaseApp extends Application {
    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        //ObjectBox 用来生成 Box 的类
        boxStore = MyObjectBox.builder().androidContext(this).build();

        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(this);
            Log.i("ObjectBrowser", "Started: " + started);
        }

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"11LpYgvK8tXvdRsaPdg5LCoP-gzGzoHsz","YrVzFsrbVsIqfCrDJlxmnLiY");
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
    }

    public static BoxStore getBoxStore() { return boxStore; }
}

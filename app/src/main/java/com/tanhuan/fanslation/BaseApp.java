package com.tanhuan.fanslation;
import android.app.Application
;
import android.util.Log;
import android.view.View;

import com.tanhuan.fanslation.entity.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.BoxStoreBuilder;
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
    }

    public static BoxStore getBoxStore() { return boxStore; }
}

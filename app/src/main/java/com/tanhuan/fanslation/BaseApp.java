package com.tanhuan.fanslation;
import android.app.Application
;

import io.objectbox.BoxStore;
import io.objectbox.BoxStoreBuilder;

public class BaseApp extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        //ObjectBox 用来生成 Box 的类
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public BoxStore getBoxStore() { return boxStore; }
}

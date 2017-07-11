package com.cheguo.tuochenew.app;

import android.app.Application;

import com.cheguo.tuochenew.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by chenyao on 2017/7/11.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.getInstance().init(this);
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        Logger.e("MyApplication 初始化完成。。。");
    }
}

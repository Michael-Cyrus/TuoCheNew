package com.cheguo.tuochenew.app;

import android.content.Context;

import com.cheguo.tuochenew.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chenyao on 2017/7/11.
 */

public class MyApp {

    private static MyApp instance;
    private Context context;
    private static EventBus mBus;

    private MyApp() {}

    public static MyApp getInstance(){
        if (null == instance) {
            instance = new MyApp();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
        // EventBus 事件传递
        mBus = EventBus.getDefault();
        // SharedPreferences 初始化
        SPUtils.init(context);
    }

    public static EventBus getBus() {
        return mBus;
    }
}

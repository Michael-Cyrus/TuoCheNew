package com.cheguo.tuochenew.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.cheguo.tuochenew.app.MyApp;
import com.cheguo.tuochenew.event.NothingEvent;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by chenyao on 2017/7/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        //设置沉浸式状态栏
        setStatusBar(true);
        //注册eventbus
        MyApp.getBus().register(this);
        //注册注解butterknife
        ButterKnife.bind(this);
        initView();
        /* Dev 分支新增内容 */
    }

    protected void setStatusBar(boolean statusBarEnabled) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        if (statusBarEnabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    @Subscribe
    public void onEvent(NothingEvent event){}

    protected abstract int getLayoutResID();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getBus().unregister(this);
    }
}

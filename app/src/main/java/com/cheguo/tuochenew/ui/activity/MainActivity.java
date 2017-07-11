package com.cheguo.tuochenew.ui.activity;

import android.app.Activity;
import android.content.Intent;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    protected static void startActivity(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}

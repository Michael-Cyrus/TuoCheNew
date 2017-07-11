package com.cheguo.tuochenew.ui.activity;

import android.os.Handler;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.base.BaseActivity;
import com.cheguo.tuochenew.utils.Constant;
import com.cheguo.tuochenew.utils.SPUtils;

/**
 * Created by chenyao on 2017/7/11.
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        toSplashActivity();
    }

    private void toSplashActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SPUtils.getBoolean(Constant.KEY_IS_FIRST_START, true)) {
                    SplashActivity.startActivity(WelcomeActivity.this);
                }else{
                    MainActivity.startActivity(WelcomeActivity.this);
                }
                finish();
            }
        }, Constant.DELAY_MILLIS);
    }
}

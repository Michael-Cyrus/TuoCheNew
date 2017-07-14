package com.cheguo.tuochenew.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.adapter.QuickPageAdapter;
import com.cheguo.tuochenew.base.BaseActivity;
import com.cheguo.tuochenew.ui.view.viewpagerindicator.CirclePageIndicator;
import com.cheguo.tuochenew.utils.Constant;
import com.cheguo.tuochenew.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by chenyao on 2017/7/11.
 */

public class SplashActivity extends BaseActivity {

    private int[] splashImageIdArr = {R.mipmap.splash_1, R.mipmap.splash_2, R.mipmap.splash_3, R.mipmap.splash_4};
    private static final float ZOOM_MAX = 1.3f;
    private static final float ZOOM_MIN = 1.0f;

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;
    @Bind(R.id.over_tv)
    TextView overTv;

    protected static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, SplashActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        QuickPageAdapter pageAdapter = new QuickPageAdapter(getImageList());
        viewpager.setAdapter(pageAdapter);
        indicator.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == splashImageIdArr.length - 1) {
                    overTv.setVisibility(View.VISIBLE);
                    playHeartbeatAnimation();
                    indicator.setVisibility(View.GONE);
                } else {
                    overTv.setVisibility(View.GONE);
                    indicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private List getImageList() {
        List list = new ArrayList();
        for (int i = 0; i < splashImageIdArr.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(splashImageIdArr[i]);
            list.add(imageView);
        }
        return list;
    }

    private void playHeartbeatAnimation() {
        /**
         * 放大动画
         */
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new ScaleAnimation(ZOOM_MIN, ZOOM_MAX, ZOOM_MIN, ZOOM_MAX, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        animationSet.addAnimation(new AlphaAnimation(1.0f, 0.8f));

        animationSet.setDuration(500);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /**
                 * 缩小动画
                 */
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(new ScaleAnimation(ZOOM_MAX, ZOOM_MIN, ZOOM_MAX, ZOOM_MIN, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
                animationSet.addAnimation(new AlphaAnimation(0.8f, 1.0f));
                animationSet.setDuration(600);
                animationSet.setInterpolator(new DecelerateInterpolator());
                animationSet.setFillAfter(false);
                // 实现心跳的View
                overTv.startAnimation(animationSet);
            }
        });
        // 实现心跳的View
        overTv.startAnimation(animationSet);
    }

    @OnClick(R.id.over_tv)
    public void gotoHomePage() {
        SPUtils.putBoolean(Constant.KEY_IS_FIRST_START, false);
        MainActivity.startActivity(this);
        finish();
    }
}

package com.cheguo.tuochenew.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.adapter.FragmentAdapter;
import com.cheguo.tuochenew.base.BaseActivity;
import com.cheguo.tuochenew.ui.fragment.HomeFragment;
import com.cheguo.tuochenew.ui.fragment.HomeFragment2;
import com.cheguo.tuochenew.ui.fragment.HomeFragment3;
import com.cheguo.tuochenew.ui.fragment.HomeFragment4;
import com.cheguo.tuochenew.ui.fragment.TrustOrderCenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;

    private FragmentAdapter adapter;
    //ViewPage选项卡页面集合
    private List<Fragment> mFragments;
    //Tab标题集合
    private List<String> mTitles;
    /**
     * 图片数组
     */
    private int[] mImgs=new int[]{R.drawable.selector_tab_home, R.drawable.selector_tab_trust_order, R.drawable.selector_tab_attention,
            R.drawable.selector_tab_me};

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        String[] titles = getResources().getStringArray(R.array.admin_tab_titles);
        mTitles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mTitles.add(titles[i]);
        }

        mFragments = new ArrayList<>();
            mFragments.add(HomeFragment.newInstance(1));
            mFragments.add(HomeFragment2.newInstance(1));
            mFragments.add(HomeFragment3.newInstance(1));
//            mFragments.add(HomeFragment4.newInstance(1));
            mFragments.add(TrustOrderCenterFragment.newInstance());
        adapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来

        mTabLayout.setSelectedTabIndicatorHeight(0);
        for (int i = 0; i < mTitles.size(); i++) {
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_tab);
                TextView textView = (TextView) itemTab.getCustomView().findViewById(R.id.tv_name);
                textView.setText(mTitles.get(i));
                ImageView imageView= (ImageView) itemTab.getCustomView().findViewById(R.id.iv_img);
                imageView.setImageResource(mImgs[i]);
            }
        }
        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    protected static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            super.onBackPressed();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
        }
    }
}

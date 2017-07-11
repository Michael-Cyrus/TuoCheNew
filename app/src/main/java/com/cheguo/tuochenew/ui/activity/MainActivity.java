package com.cheguo.tuochenew.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.base.BaseActivity;
import com.cheguo.tuochenew.ui.fragment.FragmentAdapter;
import com.cheguo.tuochenew.ui.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;

    private String[] titles = new String[]{"微信", "通讯录", "发现", "我"};
    private FragmentAdapter adapter;
    //ViewPage选项卡页面集合
    private List<Fragment> mFragments;
    //Tab标题集合
    private List<String> mTitles;
    /**
     * 图片数组
     */
    private int[] mImgs=new int[]{R.drawable.selector_tab_weixin, R.drawable.selector_tab_friends, R.drawable.selector_tab_find,
            R.drawable.selector_tab_me};

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTitles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mTitles.add(titles[i]);
        }

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(TabFragment.newInstance(i));
        }
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
}

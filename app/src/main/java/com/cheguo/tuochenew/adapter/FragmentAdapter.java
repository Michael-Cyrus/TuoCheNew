package com.cheguo.tuochenew.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 适配器
 * Created by lijuan on 2016/8/23.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    public List<Fragment> fragmentList;
    private List<String> titles;

    /**
     * 构造方法
     */
    public FragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.fragmentList = list;
        this.titles = titles;
        Logger.e("fragmentList 长度："+ fragmentList.size());
        Logger.e("titles 长度："+ titles.size());
    }

    /**
     * 返回显示的Fragment总数
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 返回要显示的Fragment的某个实例
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * 返回每个Tab的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
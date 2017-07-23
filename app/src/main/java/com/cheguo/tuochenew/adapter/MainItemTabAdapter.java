package com.cheguo.tuochenew.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainItemTabAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<Fragment> fragmentList = new ArrayList<>();

    public MainItemTabAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.titles = titles;
        this.fragmentList = fragmentList;
    }

    public MainItemTabAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position >= 11){
            Logger.e("position: "+ position);
            return null;
        }
        if(titles!=null){
            return titles[position];
        }else {
            return null;
        }
    }
}

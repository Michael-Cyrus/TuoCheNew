package com.cheguo.tuochenew.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.adapter.FragmentAdapter;
import com.cheguo.tuochenew.base.BaseLazyFragment;
import com.cheguo.tuochenew.ui.view.CenterTitleToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class TrustOrderCenterFragment extends BaseLazyFragment {

    @Bind(R.id.toolbar)
    CenterTitleToolbar mToolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private List<String> mTitlesList;
    private List<Fragment> fragmentList = new ArrayList<>();

    public static TrustOrderCenterFragment newInstance() {
        TrustOrderCenterFragment fragment = new TrustOrderCenterFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trust_order;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mToolbar.setTitle("派单中心");
        mToolbar.inflateMenu(R.menu.search);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
//                        EventUtil.sendEvent(new StartBrotherEvent(RefactorSearchFragment.newInstance()));
                        break;
                }
                return true;
            }
        });

        int[] showStatusArr=getResources().getIntArray(R.array.order_item_show_num);
        String[] titleArr=getResources().getStringArray(R.array.order_status_str);
        mTitlesList = new ArrayList<>();
        for (int i = 0; i < titleArr.length; i++) {
            fragmentList.add(TabFragment.newInstance(showStatusArr[i]));
            mTitlesList.add(titleArr[i]);
            tabLayout.addTab(tabLayout.newTab().setText(mTitlesList.get(i)));
        }
    }

    @Override
    public void onFirstUserVisible() {
//        viewpager.setOffscreenPageLimit(mTitlesList.length);
        viewpager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragmentList, mTitlesList));
        tabLayout.setupWithViewPager(viewpager);
    }

    //    @Override
//    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
//        super.onEnterAnimationEnd(savedInstanceState);
//    }

}
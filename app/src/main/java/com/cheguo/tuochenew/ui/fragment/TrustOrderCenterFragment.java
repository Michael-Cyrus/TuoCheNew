package com.cheguo.tuochenew.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.adapter.MainItemTabAdapter;
import com.cheguo.tuochenew.base.BaseLazyFragment;
import com.cheguo.tuochenew.ui.view.CenterTitleToolbar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class TrustOrderCenterFragment extends BaseLazyFragment {
    public final String TAG = this.getClass().getSimpleName();
    @Bind(R.id.toolbar)
    CenterTitleToolbar mToolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private List<String> mTitlesList;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titleArr;
    private String[] mTabTitles;

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
    protected void afterCreate(View view, Bundle savedInstanceState) {
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
        titleArr = getResources().getStringArray(R.array.order_status_str);
        mTabTitles =new String[titleArr.length];
        fragmentList.clear();
        for (int i = 0; i < titleArr.length; i++) {
            fragmentList.add(TabFragment.newInstance(showStatusArr[i]));
            mTabTitles[i]=titleArr[i];
            tabLayout.addTab(tabLayout.newTab().setText(titleArr[i]));
        }
    }

    @Override
    protected void lazyLoad() {
        Logger.e(TAG);
        viewpager.setAdapter(new MainItemTabAdapter(getChildFragmentManager(), fragmentList, mTabTitles));
        tabLayout.setupWithViewPager(viewpager);
    }
//    @Override
//    public void onFirstUserVisible() {
////        viewpager.setOffscreenPageLimit(mTitlesList.length);
//    }

    //    @Override
//    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
//        super.onEnterAnimationEnd(savedInstanceState);
//    }

}
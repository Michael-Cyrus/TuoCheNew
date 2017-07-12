package com.cheguo.tuochenew.ui.fragment;

import android.os.Bundle;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.base.LazyBaseFragment;

/**
 * Created by chenyao on 2017/7/12.
 */

public class HomeFragment extends LazyBaseFragment {


    public static HomeFragment newInstance(int index){
        Bundle bundle = new Bundle();
        bundle.putInt("index", 'A' + index);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tab_home_fragment;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }
}

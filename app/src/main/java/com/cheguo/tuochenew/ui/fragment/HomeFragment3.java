package com.cheguo.tuochenew.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.base.BaseLazyFragment;
import com.cheguo.tuochenew.ui.view.CenterTitleToolbar;
import com.orhanobut.logger.Logger;

import butterknife.Bind;

/**
 * Created by chenyao on 2017/7/12.
 */

public class HomeFragment3 extends BaseLazyFragment {
    public final String TAG = this.getClass().getSimpleName();

    @Bind(R.id.toolbar)
    CenterTitleToolbar mToolbar;
    @Bind(R.id.iv_img)
    ImageView ivImg;
//    @Bind(R.id.toolbar_center_tv)
//    TextView toolbarCenterTv;
    private SearchView searchView;

    public static HomeFragment3 newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", 'A' + index);
        HomeFragment3 fragment = new HomeFragment3();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void afterCreate(View view, Bundle savedInstanceState) {
        Logger.e("afterCreate");
        ((AppCompatActivity)mContext).setSupportActionBar(mToolbar);
//        mToolbar.inflateMenu(R.menu.main3);
        initView();
    }

    private void initView() {
//        mToolbar.setTitleTextColor(ContextCompat.getColor(mContext, R.color.black));
        mToolbar.setTitleTextAppearance(mContext, R.style.mToolbarTitle);
        mToolbar.setTitle("关注");
        mToolbar.getMenu().clear();
        mToolbar.inflateMenu(R.menu.main3);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("onActivityCreated");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.e("onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(mContext,"action_settings!",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void lazyLoad() {
        Logger.e(TAG);
    }
}

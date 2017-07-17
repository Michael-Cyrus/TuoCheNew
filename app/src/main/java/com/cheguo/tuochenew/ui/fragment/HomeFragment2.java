package com.cheguo.tuochenew.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.base.BaseFragment;
import com.cheguo.tuochenew.base.BaseLazyFragment;
import com.orhanobut.logger.Logger;

import butterknife.Bind;

/**
 * Created by chenyao on 2017/7/12.
 */

public class HomeFragment2 extends BaseFragment {
    public final String TAG = this.getClass().getSimpleName();

//    @Bind(R.id.mToolbar)
//    CenterTitleToolbar mToolbar;
    @Bind(R.id.iv_img)
    ImageView ivImg;
//    @Bind(R.id.toolbar_center_tv)
//    TextView toolbarCenterTv;
    private SearchView searchView;

    public static HomeFragment2 newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", 'A' + index);
        HomeFragment2 fragment = new HomeFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        showToolBar(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        Logger.e("afterCreate");
        ((AppCompatActivity)mContext).setSupportActionBar(mToolbar);
//        mToolbar.inflateMenu(R.menu.main2);
        initView();
    }

    private void initView() {
//        mToolbar.setTitleTextColor(ContextCompat.getColor(mContext, R.color.black));
        mToolbar.setTitleTextAppearance(mContext, R.style.mToolbarTitle);
        mToolbar.setTitle("接单大厅");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("onActivityCreated");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mToolbar.getMenu().clear();
        mToolbar.inflateMenu(R.menu.main2);
        // 获取ToolBar 的Menu控件采用以下方式获取，从ToolBar中获取Menu，然后获取Item控件
//        MenuItem search = mToolbar.getMenu().findItem(R.id.ab_search);
//        menu.clear();
//        inflater.inflate(R.menu.main2, menu);    // 不能使用这种方式给ToolBar添加Menu
//        search.setVisible(true);
        Logger.e("onCreateOptionsMenu");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Logger.e("onPrepareOptionsMenu");
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
}

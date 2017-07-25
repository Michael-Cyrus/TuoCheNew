package com.cheguo.tuochenew.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cheguo.tuochenew.R;
import com.cheguo.tuochenew.ui.view.CenterTitleToolbar;
import com.cheguo.tuochenew.utils.ScreenUtil;

import butterknife.ButterKnife;

/**
 * Created by chenyao on 2017/7/12.
 */

public abstract class BaseFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();
    private boolean showImmersion;
    protected Context mContext;
    private View mRootView;

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() <= 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(showImmersion);
        }
        mRootView = LayoutInflater.from(mContext).inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        afterCreate(mRootView, savedInstanceState);

        return mRootView;
    }

    public void setShowImmersion(boolean showImmersion) {
        this.showImmersion = showImmersion;
    }

    /**
     * 显示Toolbar
     *
     * @param mToolbar
     * @param show true:显示,false:隐藏
     */
    public void showOrHideToolBar(CenterTitleToolbar mToolbar, boolean show) {
        if (mToolbar == null) {
            Log.e(TAG, "Toolbar is null.");
        } else {
            int paddingTop = mToolbar.getPaddingTop();
            int paddingBottom = mToolbar.getPaddingBottom();
            int paddingLeft = mToolbar.getPaddingLeft();
            int paddingRight = mToolbar.getPaddingRight();
            int statusHeight = ScreenUtil.getStatusHeight(mContext);
            ViewGroup.LayoutParams params = mToolbar.getLayoutParams();
            int height = params.height;
            /**
             * 利用状态栏的高度，4.4及以上版本给Toolbar设置一个paddingTop值为status_bar的高度，
             * Toolbar延伸到status_bar顶部
             **/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(show);
            }
            if (show) {
                paddingTop += statusHeight;
                height += statusHeight;
            } else {
                paddingTop -= statusHeight;
                height -= statusHeight;
            }
            params.height = height;
            mToolbar.setLayoutParams(params);
            mToolbar.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            mToolbar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
    /**
     * 设置透明状态栏
     * 对4.4及以上版本有效
     *
     * @param on
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        afterCreate(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(View view, Bundle savedInstanceState);

    /**
     * 设置导航图标为返回按钮
     * @param toolbar
     */
    protected void setUpButton(Toolbar toolbar) {
//        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getFragmentManager().popBackStackImmediate())
                    getActivity().finish();
            }
        });
    }
    /**
     * 在当前Fragment开启另外一个Fragment
     * @param fragment
     */
    protected void startFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left);
        ft.replace(getId(), fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getClass().getName());
    }
}

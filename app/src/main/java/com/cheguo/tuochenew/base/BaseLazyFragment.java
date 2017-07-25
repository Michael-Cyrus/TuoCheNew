package com.cheguo.tuochenew.base;

/**
 * 这是一个懒加载fragment基类
 * http://www.cgw360.com
 *
 * @author：cg123
 * @create：2016-01-15 14:26
 */
public abstract class BaseLazyFragment extends BaseFragment {
    private static final String TAG = BaseLazyFragment.class.getSimpleName();
    protected boolean isVisible;
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible(){}
}

package com.cheguo.tuochenew.utils;

import android.content.Context;

/**
 * Created by chenyao on 2017/7/13.
 */

public class Util {

    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    public static int getStatusBarHeight(){
        double statusBarHeight = Math.ceil(25 * mContext.getResources().getDisplayMetrics().density);
        return (int)statusBarHeight;
    }
}

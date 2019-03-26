package com.tanhuan.fanslation.util;

import android.content.Context;

public class ViewUtil {

    //convert dp to px
    public static int dp2px(Context context, int dp) {
        int px = (int)(dp * context.getResources().getDisplayMetrics().density + 0.5f);
        return px;
    }

    //convert px to dp
    public static int px2dp(Context context, int px) {
        int dp = (int)(px / context.getResources().getDisplayMetrics().density + 0.5f);
        return dp;
    }
}

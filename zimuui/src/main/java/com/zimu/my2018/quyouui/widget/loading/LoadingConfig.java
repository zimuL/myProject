package com.zimu.my2018.quyouui.widget.loading;

import android.view.View;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class LoadingConfig {
    private static int loadingLayoutId = View.NO_ID;
    private static int preLoadingLayoutId = View.NO_ID;
    private static int emptyLayoutID = View.NO_ID;
    private static int errorLayoutID = View.NO_ID;


    public static void init(int configLoadingLayoutId, int configPreLoadingLayoutId, int configEmptyLayoutID, int configErrorLayoutID) {
        loadingLayoutId = configLoadingLayoutId;
        preLoadingLayoutId = configPreLoadingLayoutId;
        emptyLayoutID = configEmptyLayoutID;
        errorLayoutID = configErrorLayoutID;
    }

    public static int getLoadingLayoutId() {
        return loadingLayoutId;
    }

    public static int getPreLoadingLayoutId() {
        return preLoadingLayoutId;
    }


    public static int getEmptyLayoutID() {
        return emptyLayoutID;
    }

    public static int getErrorLayoutID() {
        return errorLayoutID;
    }
}

package com.zimu.my2018.appcase.scenic.scenicfreehandmap;

import android.content.Context;
import javax.inject.Inject;

/**
 * @author hxl
 * @Package com.haichou.xlqy.appcase.scenic.scenicfreehandmap
 * @Description: $description
 * @date 2018/10/09
 */
public class ScenicFreehandMapPresenter implements ScenicFreehandMapContract.Presenter{

    private Context mContext;
    private ScenicFreehandMapContract.View mView;

    @Inject
    public ScenicFreehandMapPresenter() {

    }

    @Override
    public void attachView(ScenicFreehandMapContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }


}

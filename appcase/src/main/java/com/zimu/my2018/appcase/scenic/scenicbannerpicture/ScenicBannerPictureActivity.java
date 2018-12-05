package com.zimu.my2018.appcase.scenic.scenicbannerpicture;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.scenic.scenicbannerpicture.adapter.ScenicBannerPictureAdapter;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayImageBean;
import com.zimu.my2018.quyouui.core.activity.PhotoDetailActivity;
import com.zimu.my2018.quyouui.core.base.BaseRecycleViewActivity;
import com.zimu.my2018.quyouui.widget.data.photo.NetPhotoData;
import com.zimu.my2018.quyouui.widget.dividerdecoration.SpaceItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：scenic banner pic
 * Created by hxl on 2018/10/27
 */
public class ScenicBannerPictureActivity extends BaseRecycleViewActivity implements
        ScenicBannerPictureContract.View{

    @Inject
    ScenicBannerPicturePresenter mPresenter;

    private ScenicBannerPictureAdapter mAdapter;

    private List<ArrayImageBean> mArrayImage;
    List<NetPhotoData> netList = new ArrayList<>() ;

    public static Intent getDiyIntent(Context context, List<ArrayImageBean> arrayImage) {
        Intent intent = new Intent(context, ScenicBannerPictureActivity.class);
        intent.putExtra("images", (Serializable) arrayImage);
        return intent;
    }

    @Override
    protected void initAppPresenter() {
        super.initAppPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void destroyAppPresenter() {
        super.destroyAppPresenter();
        mPresenter.detachView();
    }

    @Override
    protected void initParam() {
        super.initParam();
        Intent intent = getIntent();
        mArrayImage = (List<ArrayImageBean>) intent.getSerializableExtra("images");
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("景区图片");
    }

    @Override
    protected BaseQuickAdapter getQuickAdapter() {
        mAdapter = new ScenicBannerPictureAdapter(R.layout.item_scenic_banner_pic_view) ;
        return mAdapter;
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,
                LinearLayoutManager.VERTICAL, false) ;
        return gridLayoutManager;
    }

    @NonNull
    @Override
    protected RecyclerView.ItemDecoration getDividerDecoration() {
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.dp_10), 2);
        return itemDecoration;
    }

    @Override
    protected void loadData() {
        if (null != mArrayImage && mArrayImage.size() > 0) {
            setNewData(mArrayImage);
            for (int i = 0; i< mArrayImage.size();i++) {
                ArrayImageBean imageBean = mArrayImage.get(i);
                String resource_url = imageBean.getResource_url();
                NetPhotoData data = new NetPhotoData() ;
                data.setNetPath(ScenicType.baseUrl + resource_url);
                netList.add(data);
            }
        }
        showContent(true);
    }

    // item click
    @Override
    protected void onRecycleItemClick(BaseQuickAdapter adapter1, View view, int position) {
        super.onRecycleItemClick(adapter1, view, position);
        startActivity(PhotoDetailActivity.getDiyIntent(ScenicBannerPictureActivity.this,
                netList, position, false, false));
    }
}

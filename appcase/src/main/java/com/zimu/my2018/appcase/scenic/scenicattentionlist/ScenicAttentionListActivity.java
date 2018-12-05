package com.zimu.my2018.appcase.scenic.scenicattentionlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.scenic.scenicattentionlist.adapter.ScenicAttentionListAdapter;
import com.zimu.my2018.appcase.scenic.scenicdetail.ScenicDetailActivity;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttention;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttentionData;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttentionListData;
import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;
import com.zimu.my2018.quyouui.widget.dividerdecoration.DividerDecoration;
import com.zimu.my2018.quyouui.widget.swipeitem.SwipeItemLayout;
import com.zimu.my2018.utils.ViewUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：
 * 描述：scenic attention list
 * Created by hxl on 2018/10/26
 */
public class ScenicAttentionListActivity extends BaseTitleActivity implements
        ScenicAttentionListContract.View,
        BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R2.id.rv_scenic_attention)
    RecyclerView rv_scenic_attention;

    @Inject
    ScenicAttentionListPresenter mPresenter;

    private ScenicAttentionListAdapter mAdapter;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, ScenicAttentionListActivity.class);
        return intent;
    }

    @Override
    protected void initParam() {
        super.initParam();
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
    protected int getAppView() {
        return R.layout.activity_scenic_attention_list_view;
    }

    @Override
    protected void initView() {
        setTitle("景区关注");
        setRefreshLayoutInVisble();
        setRecyclerView();
    }

    private void setRecyclerView() {
        mAdapter = new ScenicAttentionListAdapter(R.layout.item_scenic_attention_view);
        DividerDecoration dividerDecoration = ViewUtil.getDividerDecoration(this,
                DeviceUtils.dip2px(this, 1), 0, 0);
        ViewUtil.initRecycleView(this, rv_scenic_attention,dividerDecoration);
        rv_scenic_attention.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnItemChildClickListener(this);
        /*recyclerView侧滑实现*/
        rv_scenic_attention.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
    }

    @Override
    protected void loadData() {
        mPresenter.getScenicAttentionListData();
    }

    private void setData(ScenicAttentionData response) {
        if (null != response) {
            List<ScenicAttentionListData> focalList = response.getFocalList();
            if (null != focalList && focalList.size() > 0) {
                mAdapter.setNewData(focalList);
            }
        }
    }

    // 删除关注
    private void deleteAttention(int position) {
        List<ScenicAttentionListData> data = mAdapter.getData();
        ScenicAttentionListData listData = data.get(position);
        ScenicBean scenic = listData.getScenic();
        int scenic_id = scenic.getScenic_id();
        mPresenter.cancelScenicAttention(scenic_id);
    }

    private void itemClick(int position) {
        List<ScenicAttentionListData> data = mAdapter.getData();
        ScenicAttentionListData listData = data.get(position);
        ScenicBean scenic = listData.getScenic();
        int scenic_id = scenic.getScenic_id();
        startActivity(ScenicDetailActivity.getDiyIntent(this, scenic_id));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int id = view.getId();
        if (id == R.id.tv_delete) {
            deleteAttention(position);
        } else if (id == R.id.ll_main) {
            itemClick(position);
        }
    }

    @Override
    public void onGetScenicAttentionListDataSuccess(ScenicAttentionData response) {
        showContent(true);
        setData(response);
    }

    @Override
    public void onGetScenicAttentionListDataFailed(String msg) {
        Log.e("hxl", "onGetScenicAttentionListDataFailed:  " + msg );
    }

    @Override
    public void onCancelScenicAttentionSuccess(ScenicAttention response) {
        String type = response.getType();
        if ("1".equals(type)) {
            showToast("取消关注成功！");
            mPresenter.getScenicAttentionListData();
        }
    }

    @Override
    public void onCancelScenicAttentionFailed(String msg) {
        Log.e("hxl", "onCancelScenicAttentionFailed: " + msg );
    }
}

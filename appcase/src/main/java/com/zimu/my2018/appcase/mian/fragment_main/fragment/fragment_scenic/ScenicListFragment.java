package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_scenic;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_scenic.adapter.MainScenicAdapter;
import com.zimu.my2018.appcase.scenic.scenicdetail.ScenicDetailActivity;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicListData;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicsBean;
import com.zimu.my2018.quyoulib.core.base.BaseZimuFragment;
import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;
import com.zimu.my2018.quyouui.widget.viewpager.WrapContentHeightViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@PerActivity
public class ScenicListFragment extends BaseZimuFragment implements ScenicListContract.View,
        BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.rv_main_scenic)
    RecyclerView rv_main_scenic;

    @Inject
    ScenicListPresenter mPresenter;

    private MainScenicAdapter mAdapter;

    private WrapContentHeightViewPager pager;

    private int mStartIndex = 1;   // 起始下标
    private int mEndIndex = 6;     // 结束下标
    private int mLng = 1000212;    // 经度
    private int mLat = 1212222;    // 纬度
    private int mPageType = 1;     // 数据类型 "1"综合查询，"2"按距离查询，"3"按人气查询，"4"按评分查询
    private int mScenictype = 0;   // 景区类型"-1"当前不可查，"0"景区 ，"1"乡镇类景区

    private int fragmentID;

    @Inject
    public ScenicListFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ScenicListFragment(WrapContentHeightViewPager pager, int fragmentID) {
        this.pager = pager;
        this.fragmentID = fragmentID;
    }

    @Override
    protected void initParams() {

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
    protected int getLayoutId() {
        return R.layout.fragment_scenic_list_view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        pager.setObjectForPosition(self,fragmentID);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new MainScenicAdapter(R.layout.item_main_scenic_view) ;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2,
                LinearLayoutManager.VERTICAL, false) ;
        rv_main_scenic.setLayoutManager(gridLayoutManager);
        rv_main_scenic.setHasFixedSize(true);
        rv_main_scenic.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        super.loadData();
        mPresenter.getScenicList(mStartIndex, mEndIndex, mLng, mLat, mPageType, mScenictype);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<ScenicsBean> data = mAdapter.getData();
        ScenicsBean scenicsBean = data.get(position);
        ScenicBean scenic = scenicsBean.getScenic();
        int scenic_id = scenic.getScenic_id();
        startActivity(ScenicDetailActivity.getDiyIntent(getContext(), scenic_id));
    }

    @Override
    public void onGetScenicListSuccess(ScenicListData response) {
        if (null != response && null != response.getScenics() && response.getScenics().size() > 0) {
            mAdapter.setNewData(response.getScenics());
        }
    }

    @Override
    public void onGetScenicListFailed(String msg) {
        Log.e("hxl", "onGetScenicListFailed: " + msg );
    }
}

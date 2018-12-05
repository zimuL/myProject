package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_footprint;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_footprint.adapter.FootPrintChooseAdapter;
import com.zimu.my2018.quyouapi.data.main.ScenicFootPrintChooseListData;
import com.zimu.my2018.quyoulib.core.base.BaseHcFragment;
import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;
import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyouui.widget.dividerdecoration.DividerDecoration;
import com.zimu.my2018.quyouui.widget.viewpager.WrapContentHeightViewPager;
import com.zimu.my2018.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@PerActivity
public class FootPrintChooseFragment extends BaseHcFragment implements FootPrintChooseContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.rv_foot_print)
    RecyclerView rv_foot_print;

    @Inject
    FootPrintChoosePresenter mPresenter;

    private FootPrintChooseAdapter mAdapter;

    private WrapContentHeightViewPager pager;
    private int fragmentID;

    @Inject
    public FootPrintChooseFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FootPrintChooseFragment(WrapContentHeightViewPager pager, int fragmentID) {
        this.pager = pager;
        this.fragmentID = fragmentID;
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
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_foot_print_choiceness_view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        pager.setObjectForPosition(self,fragmentID);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new FootPrintChooseAdapter(R.layout.item_main_foot_choose_view) ;
        DividerDecoration dividerDecoration = ViewUtil.getDividerDecoration(getContext(),
                DeviceUtils.dip2px(getContext(), 3), 0, 0);
        ViewUtil.initRecycleView(getContext(), rv_foot_print, dividerDecoration);
        rv_foot_print.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        super.loadData();
        List<ScenicFootPrintChooseListData> list = new ArrayList<>() ;
        String[] headerUrl = {"http://m.tuniucdn.com/filebroker/cdn/snc/c2/4d/c24d918759a64a0ca46d9d122fa7c053_w500_h280_c1_t0.jpg",
                "http://m.tuniucdn.com/fb2/t1/G1/M00/16/D0/Cii9EFbX73-IVWNBAAWFnRCQLowAACdzwGkY9wABYW1365_w500_h280_c1_t0.jpg",
                "http://m.tuniucdn.com/fb2/t1/G1/M00/2F/FE/Cii9EVboGu2IS_U9ABIIjqJxJ1AAACoUAINgXwAEgim602_w500_h280_c1_t0.jpg"};
        String[] names = {"普普", "啦啦", "珂珂"};
        String[] contentUrls = {"http://m.tuniucdn.com/fb2/t1/G2/M00/40/DF/Cii-TFe1aGWIEf4dAB4WTi2eS0sAABXugOUTAYAHhZm437_w500_h280_c1_t0.jpg",
                "http://m.tuniucdn.com/filebroker/cdn/online/d2/cd/d2cdda0f_w500_h280_c1_t0.jpg",
                "http://m.tuniucdn.com/filebroker/cdn/snc/c2/4d/c24d918759a64a0ca46d9d122fa7c053_w500_h280_c1_t0.jpg"};
        String[] briefs = {"苏堤南起南屏山麓，北到栖霞岭下，全长近三公里，他是北宋大诗人苏东坡任杭州知州时，疏浚西湖，利用挖出的葑泥构筑而成。",
        "苏堤南起南屏山麓，北到栖霞岭下，全长近三公里，他是北宋大诗人苏东坡任杭州知州时，疏浚西湖，利用挖出的葑泥构筑而成。",
        "苏堤南起南屏山麓，北到栖霞岭下，全长近三公里，他是北宋大诗人苏东坡任杭州知州时，疏浚西湖，利用挖出的葑泥构筑而成。"};

        for (int i = 0; i< headerUrl.length; i++) {
            ScenicFootPrintChooseListData data = new ScenicFootPrintChooseListData() ;
            data.setHeaderUrl(headerUrl[i]);
            data.setName(names[i]);
            data.setContentUrl(contentUrls[i]);
            data.setBrief(briefs[i]);
            list.add(data);
        }
        mAdapter.setNewData(list);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}

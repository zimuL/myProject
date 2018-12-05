package com.zimu.my2018.appcase.mian.fragment_main;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.mian.fragment_main.adapter.MainViewPagerAdapter;
import com.zimu.my2018.appcase.mian.fragment_main.adapter.ScenicWeekendAdapter;
import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_footprint.FootPrintChooseFragment;
import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_scenic.ScenicListFragment;
import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_traveller.TravellerFragment;
import com.zimu.my2018.appcase.mian.fragment_main.viewholder.BannerViewHolder;
import com.zimu.my2018.appcase.scenic.scenicdetail.ScenicDetailActivity;
import com.zimu.my2018.appcase.scenic.scenicnearby.ScenicNearbyActivity;
import com.zimu.my2018.appcase.search.searchscenic.SearchScenicActivity;
import com.zimu.my2018.quyouapi.data.main.ScenicWeekendListBean;
import com.zimu.my2018.quyouapi.data.main.banner.MainBannerData;
import com.zimu.my2018.quyoulib.core.base.BaseZimuFragment;
import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;
import com.zimu.my2018.quyoulib.location.LocationManager;
import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.widget.dividerdecoration.DividerDecoration;
import com.zimu.my2018.quyouui.widget.viewpager.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@PerActivity
public class MainFragment extends BaseZimuFragment implements MainFragContract.View,
        OnItemClickListener, ViewPager.OnPageChangeListener {

    @BindView(R2.id.con_ban_view)
    ConvenientBanner con_ban_view;
    @BindView(R2.id.rv_weekend)
    RecyclerView rv_weekend;
    @BindView(R2.id.tab_main)
    TabLayout tab_main;
    @BindView(R2.id.vp_main)
    WrapContentHeightViewPager vp_main;
    @BindView(R2.id.tv_main_location_city)
    TextView tv_city;
    @BindView(R2.id.tv_main_search)
    TextView tv_main_search;
    @BindView(R2.id.tv_main_nearby)
    TextView tv_main_nearby;

    @Inject
    MainFragPresenter mPresenter ;

    private ScenicWeekendAdapter mWeekendAdapter;

    private LocationManager mLocationManager;

    private List<String> tabList = new ArrayList<>() ;
    private String mCity;
    private double mLatitude, mLongitude;

    @Inject
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_view;
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected void initAppPresenter() {
        mPresenter.attachView(this);
    }

    @Override
    protected void destroyAppPresenter() {
        mPresenter.detachView();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initLocationData();
        initRecyclerView();
        initTabData();
        initViewPager();
    }

    private void initLocationData() {
        mLocationManager = new LocationManager(getContext()) ;
        mLocationManager.getLocation(new LocationManager.CallBack() {
            @Override
            public void onGetLocation(HashMap<String, Object> map) {
                mCity = (String) map.get("city");
                mLatitude = (double) map.get("latitude");
                mLongitude = (double) map.get("longitude");
                if (StringUtils.checkNullPoint(mCity)) {
                    tv_city.setText(mCity);
                }
            }
        });
    }

    private void initRecyclerView() {
        mWeekendAdapter = new ScenicWeekendAdapter(R.layout.item_scenic_weekend_view) ;
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) ;
        DividerDecoration dividerDecoration = new DividerDecoration(Color.parseColor("#f1f7fd"), DeviceUtils.dip2px(getContext(), 10), 0, 0);
        rv_weekend.addItemDecoration(dividerDecoration);
        rv_weekend.setLayoutManager(manager);
        rv_weekend.setHasFixedSize(true);
        rv_weekend.setAdapter(mWeekendAdapter);
    }

    private void initTabData() {
        String[] tabs = {"景区", "足迹精选", "旅行家"};
        tabList = Arrays.asList(tabs);
        for (int i =0; i< tabs.length; i++) {
            tab_main.addTab(tab_main.newTab().setText(tabs[i]));
        }
        tab_main.setTabMode(TabLayout.GRAVITY_FILL);
        tab_main.setSelectedTabIndicatorHeight(0);
    }

    private void initViewPager() {
        List<Fragment> fragments  = new ArrayList<>() ;
        ScenicListFragment scenicListFragment = new ScenicListFragment(vp_main, 0) ;
        FootPrintChooseFragment chooseFragment = new FootPrintChooseFragment(vp_main, 1) ;
        TravellerFragment travellerFragment = new TravellerFragment(vp_main, 2) ;
        fragments.add(scenicListFragment);
        fragments.add(chooseFragment);
        fragments.add(travellerFragment);
        MainViewPagerAdapter pagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments, tabList) ;
        vp_main.setAdapter(pagerAdapter);
        tab_main.setupWithViewPager(vp_main);

        vp_main.resetHeight(0);
    }

    @Override
    protected void initListener() {
        super.initListener();
        vp_main.addOnPageChangeListener(this);
        setViewListener(tv_main_search, this);
        setViewListener(tv_main_nearby, this);
    }

    @Override
    protected void loadData() {
        super.loadData();
        mPresenter.getBannerListData();
        setWeekendScenicData();
    }

    private void setBannerData(List<MainBannerData.DataBean> data) {
        con_ban_view.setPages(() -> {
            BannerViewHolder holder = new BannerViewHolder() ;
            return holder;
        }, data)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        con_ban_view.startTurning(3000);
    }

    private void setWeekendScenicData() {
        List<ScenicWeekendListBean> listBeans = new ArrayList<>() ;
        String[] url = {"http://m.tuniucdn.com/fb2/t1/G1/M00/94/AF/Cii9EFi37bKIH0EJABDjKyFG1ZAAAIOUAPXGxcAEOND174_w500_h280_c1_t0.jpg",
                "http://m.tuniucdn.com/fb2/t1/G1/M00/B8/C1/Cii9EFcypxiIbrzLAA73L5csElgAAFX_QGyDZIADvdH24_w500_h280_c1_t0.jpeg"};
        String[] name = {"钱江新城", "西溪湿地"};
        for (int i = 0; i < url.length;i++) {
            ScenicWeekendListBean bean = new ScenicWeekendListBean() ;
            bean.setUrl(url[i]);
            bean.setName(name[i]);
            listBeans.add(bean);
        }
        mWeekendAdapter.setNewData(listBeans);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        con_ban_view.stopTurning();
    }

    // ConvenientBanner
    @Override
    public void onItemClick(int position) {
        startActivity(ScenicDetailActivity.getDiyIntent(getContext(), 32));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        vp_main.resetHeight(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
        int id = view.getId();
        if (id == R.id.tv_main_search) {
            startActivity(SearchScenicActivity.getDiyIntent(getContext()));
        } else if (id == R.id.tv_main_nearby) {
            startActivity(ScenicNearbyActivity.getDiyIntent(getContext(), mCity, mLongitude, mLatitude));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationManager.stopLocation();
    }

    @Override
    public void onGetBannerListDataSuccess(MainBannerData response) {
        if (null != response) {
            List<MainBannerData.DataBean> data = response.getData();
            setBannerData(data);
        }
    }

    @Override
    public void onGetBannerListDataFailed(String msg) {
        Log.e("hxl", "onGetBannerListDataFailed: " + msg);
    }
}

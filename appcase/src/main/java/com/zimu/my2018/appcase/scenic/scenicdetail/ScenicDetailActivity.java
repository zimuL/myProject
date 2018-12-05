package com.zimu.my2018.appcase.scenic.scenicdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.scenic.scenicbannerpicture.ScenicBannerPictureActivity;
import com.zimu.my2018.appcase.scenic.scenicdetail.adapter.ScenicDetailAdapter;
import com.zimu.my2018.appcase.scenic.scenicdetail.adapter.ScenicDetailNearbyAdapter;
import com.zimu.my2018.appcase.scenic.scenicdetail.viewholder.BannerScenicDetailViewHolder;
import com.zimu.my2018.appcase.scenic.scenicevaluate.ScenicEvaluateActivity;
import com.zimu.my2018.appcase.scenic.scenicfreehandmap.ScenicFreehandMapActivity;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayImageBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayTextBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttention;
import com.zimu.my2018.quyouapi.data.scenic.scenicdetail.ScenicDetailData;
import com.zimu.my2018.quyouapi.data.scenic.scenicdetail.ServicePointsBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicnear.NearByscenicsBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicnear.ScenicDetailNearByData;
import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;
import com.zimu.my2018.quyouui.widget.custom.textview.ExpandTextView;
import com.zimu.my2018.quyouui.widget.dividerdecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：
 * 描述：详情
 * Created by hxl on 2018/10/8
 */
public class ScenicDetailActivity extends BaseTitleActivity implements ScenicDetailContract.View, OnItemClickListener {

    @BindView(R2.id.banner_scenic)
    ConvenientBanner banner_scenic;
    @BindView(R2.id.expand_tv_scenic_detail)
    ExpandTextView expand_tv_scenic_detail;
    @BindView(R2.id.tv_scenic_detail_name)
    TextView tv_scenic_detail_name;
    @BindView(R2.id.tv_scenic_detail_location)
    TextView tv_scenic_detail_location;
    @BindView(R2.id.tv_scenic_detail_time)
    TextView tv_scenic_detail_time;
    @BindView(R2.id.tv_scenic_detail_play_time)
    TextView tv_scenic_detail_play_time;
    @BindView(R2.id.tv_scenic_relation_way)
    TextView tv_scenic_relation_way;
    @BindView(R2.id.tv_scenic_destination_way)
    TextView tv_scenic_destination_way;
    @BindView(R2.id.tv_scenic_tickets)
    TextView tv_scenic_tickets;
    @BindView(R2.id.tv_scenic_detail_num)
    TextView tv_scenic_detail_num;
    @BindView(R2.id.rv_scenic_detail)
    RecyclerView rv_scenic_detail;
    @BindView(R2.id.tv_scenic_detail_food_num)
    TextView tv_scenic_detail_food_num;
    @BindView(R2.id.rv_scenic_detail_food)
    RecyclerView rv_scenic_detail_food;
    @BindView(R2.id.tv_scenic_detail_hotel_num)
    TextView tv_scenic_detail_hotel_num;
    @BindView(R2.id.rv_scenic_detail_hotel)
    RecyclerView rv_scenic_detail_hotel;
    @BindView(R2.id.rv_scenic_detail_evaluate)
    RecyclerView rv_scenic_detail_evaluate;
    @BindView(R2.id.tv_scenic_detail_evaluate_empty)
    TextView tv_scenic_detail_evaluate_empty;
    @BindView(R2.id.rv_scenic_detail_nearby)
    RecyclerView rv_scenic_detail_nearby;
    @BindView(R2.id.ll_scenic_detail_enter_map)
    LinearLayout ll_scenic_detail_enter_map;
    @BindView(R2.id.tv_scenic_detail_evaluate)
    TextView tv_scenic_detail_evaluate;
    @BindView(R2.id.tv_scenic_detail_attention)
    TextView tv_scenic_detail_attention;
    @BindView(R2.id.ll_scenic_detail_attention)
    LinearLayout ll_scenic_detail_attention;

    @Inject
    ScenicDetailPresenter mPresenter;

    private ScenicDetailAdapter mScenicDetailAdapter;
    private ScenicDetailAdapter mScenicFoodAdapter;
    private ScenicDetailAdapter mScenicHotelAdapter;

    private ScenicDetailNearbyAdapter mNearbyAdapter;

    private ScenicDetailData mDetailData;
    private int mScenicId;
    private String mScenicName;
    private String mMapUrl;
    private boolean mFollow;      // 景区是否关注

    public static Intent getDiyIntent(Context context, int scenicId) {
        Intent intent = new Intent(context, ScenicDetailActivity.class);
        intent.putExtra("scenicId", scenicId);
        return intent;
    }

    @Override
    protected void initParam() {
        super.initParam();
        Intent intent = getIntent();
        mScenicId = intent.getIntExtra("scenicId", 0);
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
        return R.layout.activity_scenic_detail_view;
    }

    @Override
    protected void initView() {
        setTitle("详情");
        setRefreshLayoutInVisble();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mScenicDetailAdapter = new ScenicDetailAdapter(R.layout.item_scenic_detail_view) ;
        mScenicFoodAdapter = new ScenicDetailAdapter(R.layout.item_scenic_detail_view) ;
        mScenicHotelAdapter = new ScenicDetailAdapter(R.layout.item_scenic_detail_view) ;

        mNearbyAdapter = new ScenicDetailNearbyAdapter(R.layout.item_scenic_detail_nearby_view) ;

        GridLayoutManager scenicManager = new GridLayoutManager(this, 3,
                LinearLayoutManager.VERTICAL, false) ;
        GridLayoutManager foodManager = new GridLayoutManager(this, 3,
                LinearLayoutManager.VERTICAL, false) ;
        GridLayoutManager hotelManager = new GridLayoutManager(this, 3,
                LinearLayoutManager.VERTICAL, false) ;
        GridLayoutManager nearbyManager = new GridLayoutManager(this, 3,
                LinearLayoutManager.VERTICAL, false) ;

        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(DeviceUtils
                .dip2px(this, 5)) ;

        rv_scenic_detail.setLayoutManager(scenicManager);
        rv_scenic_detail.setHasFixedSize(true);
        rv_scenic_detail.addItemDecoration(itemDecoration);
        rv_scenic_detail.setAdapter(mScenicDetailAdapter);

        rv_scenic_detail_food.setLayoutManager(foodManager);
        rv_scenic_detail_food.setHasFixedSize(true);
        rv_scenic_detail_food.addItemDecoration(itemDecoration);
        rv_scenic_detail_food.setAdapter(mScenicFoodAdapter);

        rv_scenic_detail_hotel.setLayoutManager(hotelManager);
        rv_scenic_detail_hotel.setHasFixedSize(true);
        rv_scenic_detail_hotel.addItemDecoration(itemDecoration);
        rv_scenic_detail_hotel.setAdapter(mScenicHotelAdapter);

        rv_scenic_detail_nearby.setLayoutManager(nearbyManager);
        rv_scenic_detail_nearby.setHasFixedSize(true);
        rv_scenic_detail_nearby.addItemDecoration(itemDecoration);
        rv_scenic_detail_nearby.setAdapter(mNearbyAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        setViewListener(ll_scenic_detail_enter_map, this);
        setViewListener(tv_scenic_detail_evaluate, this);
        setViewListener(ll_scenic_detail_attention, this);
        mNearbyAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<NearByscenicsBean> data = mNearbyAdapter.getData();
            NearByscenicsBean nearByscenicsBean = data.get(position);
            int scenic_id = nearByscenicsBean.getScenic().getScenic_id();
            startActivity(ScenicDetailActivity.getDiyIntent(this, scenic_id));
        });
        banner_scenic.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getScenicDetailData(mScenicId);
        setScenicFoodListData();
        setScenicHotelListData();
    }

    // banner
    private void showBannerData(List<ArrayImageBean> arrayImage) {
        banner_scenic.setPages(() -> {
            BannerScenicDetailViewHolder holder = new BannerScenicDetailViewHolder() ;
            return holder;
        }, arrayImage);
        banner_scenic.startTurning(3000);
    }

    // brief
    private void setDetailBriefData(List<ArrayTextBean> arrayText) {
        if (null != arrayText && arrayText.size() > 0) {
            ArrayTextBean arrayTextBean = arrayText.get(0);
            String introduction = arrayTextBean.getResource_introduction();
            expand_tv_scenic_detail.setText("\u3000\u3000" + introduction);
        }
    }

    // detail
    private void setScenicDetailData(ScenicBean scenic) {
        mScenicName = scenic.getScenic_name();
        String address = scenic.getAddress();
        String openingTime = scenic.getOpening_time();
        String journeyTime = scenic.getJourney_time();
        String contactWay = scenic.getContact_way();
        String unobstructed = scenic.getUnobstructed();
        String entranceTicket = scenic.getEntrance_ticket();
        mMapUrl = scenic.getMap_url();
        String latitude = String.valueOf(scenic.getNavigition_latitude());
        String longitude = String.valueOf(scenic.getNavigition_longitude());
        tv_scenic_detail_name.setText(mScenicName);
        tv_scenic_detail_location.setText(address);
        if (StringUtils.checkNullPoint(openingTime)) {
            tv_scenic_detail_time.setText(openingTime);
        }
        if (StringUtils.checkNullPoint(journeyTime)) {
            tv_scenic_detail_play_time.setText(journeyTime);
        }
        if (StringUtils.checkNullPoint(contactWay)) {
            tv_scenic_relation_way.setText(contactWay);
        }
        if (StringUtils.checkNullPoint(unobstructed)) {
            tv_scenic_destination_way.setText(unobstructed);
        }
        if (StringUtils.checkNullPoint(entranceTicket)) {
            tv_scenic_tickets.setText(entranceTicket);
        }
        mPresenter.getScenicNearData(longitude, latitude);
    }

    // scenic list
    private void setScenicDetailListData(List<ServicePointsBean> servicePoints) {
        if (null != servicePoints && servicePoints.size() > 0) {
            int size = servicePoints.size();
            tv_scenic_detail_num.setText(String.format(Locale.getDefault(), "景点(%d)", size));
            if (size > 4) {
                servicePoints = servicePoints.subList(0, 3);
                mScenicDetailAdapter.setNewData(servicePoints);
            } else {
                mScenicDetailAdapter.setNewData(servicePoints);
            }
        }
    }

    private void setScenicFoodListData() {
        List<ServicePointsBean> data = new ArrayList<>() ;
        String[] urls = {
                "resource/area/shiLinZhen/scenics/shiLinZhen/media/image/HZHC_2of3k0AJWU6SsX4Zt78D.jpg",
                "resource/area/shiLinZhen/scenics/shiLinZhen/media/image/HZHC_AuCzmoa7529XNHxOpdWt.jpg",
                "resource/area/shiLinZhen/scenics/shiLinZhen/media/image/HZHC_Ap4DKSWhJVrlOY0xUGbg.jpg"};
        String[] names = {"西塘", "西溪湿地", "湘湖"};
        for (int i = 0; i< urls.length;i++) {
            ServicePointsBean listData = new ServicePointsBean() ;
            listData.setResource_url(urls[i]);
            listData.setService_name(names[i]);
            data.add(listData);
        }
        mScenicFoodAdapter.setNewData(data);
    }

    private void setScenicHotelListData() {
        List<ServicePointsBean> data = new ArrayList<>() ;
        String[] urls = {
                "resource/area/shiLinZhen/scenics/shiLinZhen/media/image/HZHC_c4xGlvfunbN29Etie8wD.jpg",
                "resource/area/shiLinZhen/scenics/shiLinZhen/media/image/HZHC_dSDzQkmJr6yac7pYCOnq.jpg",
                "resource/area/shiLinZhen/scenics/shiLinZhen/media/image/HZHC_DUbZYJVd95LmsqAMGgFy.jpg"};
        String[] names = {"西塘", "西溪湿地", "湘湖"};
        for (int i = 0; i< urls.length;i++) {
            ServicePointsBean listData = new ServicePointsBean() ;
            listData.setResource_url(urls[i]);
            listData.setService_name(names[i]);
            data.add(listData);
        }
        mScenicHotelAdapter.setNewData(data);
    }

    private void setScenicNearbyListData(ScenicDetailNearByData response) {
        if (null != response && null != response.getNearByscenics()
                && response.getNearByscenics().size() > 0) {
            List<NearByscenicsBean> data = response.getNearByscenics();
            mNearbyAdapter.setNewData(data);
        }
    }

    private void setData(ScenicDetailData response) {
        mDetailData = response;
        List<ArrayImageBean> arrayImage = response.getArrayImage();
        List<ArrayTextBean> arrayText = response.getArrayText();
        ScenicBean scenic = response.getScenic();
        List<ServicePointsBean> servicePoints = response.getServicePoints();
        mFollow = response.isFollow();
        showBannerData(arrayImage);
        setDetailBriefData(arrayText);
        setScenicDetailData(scenic);
        setScenicDetailListData(servicePoints);
        attentionScenic();
    }

    // 关注景区
    private void attentionScenic() {
        Drawable drawable ;
        if (mFollow) {
            drawable = getResources().getDrawable(R.mipmap.ic_scenic_detail_attentioned);
            Log.e("hxl", "attentionScenic: " + "关注" );
        } else {
            drawable = getResources().getDrawable(R.mipmap.ic_scenic_detail_attention);
            Log.e("hxl", "attentionScenic: " + "取消" );
        }
        tv_scenic_detail_attention.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
        int id = view.getId();
        if (id == R.id.ll_scenic_detail_enter_map) {
            if (StringUtils.checkNullPoint(mMapUrl)) {
                startActivity(ScenicFreehandMapActivity.getDiyIntent(this, mMapUrl, mScenicName));
            } else {
                showToast("暂无景区地图！");
            }
        } else if(id == R.id.tv_scenic_detail_evaluate) {
            startActivity(ScenicEvaluateActivity.getDiyIntent(this, mScenicId));
        } else if (id == R.id.ll_scenic_detail_attention) {
            if (mFollow) {
                mPresenter.cancelScenicAttention(mScenicId);
            } else {
                mPresenter.postScenicAttention(mScenicId);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner_scenic.stopTurning();
    }

    @Override
    public void onGetScenicDetailDataSuccess(ScenicDetailData response) {
        showContent(true);
        setData(response);
    }

    @Override
    public void onGetScenicDetailDataFailed(String msg) {
        showToast(msg);
    }

    @Override
    public void onGetScenicNearDataSuccess(ScenicDetailNearByData response) {
        setScenicNearbyListData(response);
    }

    @Override
    public void onGetScenicNearDataFailed(String msg) {
        Log.e("hxl", "onGetScenicNearDataFailed: " + msg);
    }

    @Override
    public void onPostScenicAttentionSuccess(ScenicAttention response) {
        String type = response.getType();
        if (type.equals("1")) {
            mFollow = true;
            showToast("关注成功！");
            attentionScenic();
        }
    }

    @Override
    public void onPostScenicAttentionFailed(String msg) {
        Log.e("hxl", "onPostScenicAttentionFailed: " + msg );
    }

    @Override
    public void onCancelScenicAttentionSuccess(ScenicAttention response) {
        String type = response.getType();
        if (type.equals("1")) {
            mFollow = false;
            showToast("取消关注");
            attentionScenic();
        }
    }

    @Override
    public void onCancelScenicAttentionFailed(String msg) {
        Log.e("hxl", "onCancelScenicAttentionFailed: " + msg );
    }

    // banner click
    @Override
    public void onItemClick(int position) {
        List<ArrayImageBean> arrayImage = mDetailData.getArrayImage();
        startActivity(ScenicBannerPictureActivity.getDiyIntent(this, arrayImage));
    }
}

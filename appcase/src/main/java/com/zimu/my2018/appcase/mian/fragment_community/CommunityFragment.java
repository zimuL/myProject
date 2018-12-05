package com.zimu.my2018.appcase.mian.fragment_community;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.quyoulib.core.base.BaseZimuFragment;
import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;
import com.zimu.my2018.quyoulib.location.LocationManager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/11
 */
@PerActivity
public class CommunityFragment extends BaseZimuFragment implements CommunityFragContract.View, AMap.OnMyLocationChangeListener, AMap.OnMapTouchListener {

    @BindView(R2.id.map_view)
    MapView mMapView;

    @Inject
    CommunityFragPresenter mPresenter;

    private LocationManager mLocationManager;

    private AMap map;

    private boolean followMove=true;


    @Inject
    public CommunityFragment() {

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
        return R.layout.fragment_community_view;
    }

    @Override
    public void initSavedInstanceState(Bundle savedInstanceState) {
        super.initSavedInstanceState(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        if (null == map) {
            map = mMapView.getMap();
        }
        mLocationManager = new LocationManager(getContext());
        // 定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle() ;
        // 拖动地图后定位小蓝点不返回到屏幕的中心位置
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        map.setOnMyLocationChangeListener(this);
        map.setMyLocationStyle(myLocationStyle);
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        mLocationManager.startLocation();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void initListener() {
        super.initListener();
        map.setOnMapTouchListener(this);
    }

    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mMapView) {
            mMapView.onDestroy();
            mMapView = null;
        }
        map = null;
        mLocationManager.stopLocation();
    }

    // my location
    @Override
    public void onMyLocationChange(Location location) {
        if (followMove) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude) ;
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        // 用户拖动地图后，不再跟随移动，直到用户点击定位按钮
        followMove = false;
    }
}

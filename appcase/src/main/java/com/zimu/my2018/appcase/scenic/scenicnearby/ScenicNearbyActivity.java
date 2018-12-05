package com.zimu.my2018.appcase.scenic.scenicnearby;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.scenic.scenicnearby.adapter.ScenicNearbyAdapter;
import com.zimu.my2018.quyoulib.location.AddrSearchManager;
import com.zimu.my2018.quyoulib.location.LocationManager;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;
import com.zimu.my2018.utils.ViewUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * scenic nearby
 */
public class ScenicNearbyActivity extends BaseTitleActivity implements ScenicNearbyContract.View, TabLayout.OnTabSelectedListener, AddrSearchManager.CallBack, BaseQuickAdapter.OnItemClickListener, AMap.OnMyLocationChangeListener {

    @BindView(R2.id.tab_nearby)
    TabLayout tab_nearby;
    @BindView(R2.id.map_nearby)
    MapView map_nearby;
    @BindView(R2.id.rv_nearby)
    RecyclerView rv_nearby;

    @Inject
    ScenicNearbyPresenter mPresenter;

    private ScenicNearbyAdapter mNearbyAdapter;

    private LocationManager mLocationManager ;
    private AddrSearchManager mAddrSearchManager;
    private LatLonPoint mLatLonPoint;

    private AMap mMap;
    private String[] searchType = null ;
    private String mCity;
    private double mLatitude, mLongitude;


    public static Intent getDiyIntent(Context context, String city, double longitude, double latitude) {
        Intent intent = new Intent(context, ScenicNearbyActivity.class);
        intent.putExtra("city", city);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);
        return intent;
    }

    @Override
    protected void initParam() {
        super.initParam();
        Intent intent = getIntent();
        mCity = intent.getStringExtra("city");
        mLatitude = intent.getDoubleExtra("latitude", 0);
        mLongitude = intent.getDoubleExtra("longitude", 0);
        mLatLonPoint = new LatLonPoint(mLatitude, mLongitude) ;
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
    public void initSavedInstanceState(Bundle savedInstanceState) {
        super.initSavedInstanceState(savedInstanceState);
        map_nearby.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map_nearby.onSaveInstanceState(outState);
    }

    @Override
    protected int getAppView() {
        return R.layout.activity_scenic_nearby_view;
    }

    @Override
    protected void initView() {
        setRefreshLayoutInVisble();
        setTitle("附近");
        initTabView();
        initMap();
        initRecyclerView();
    }

    private void initTabView() {
        String[] tabs = getResources().getStringArray(R.array.nearbyTab);
        for (int i = 0; i < tabs.length; i++) {
            tab_nearby.addTab(tab_nearby.newTab().setText(tabs[i]));
        }
        tab_nearby.setTabMode(TabLayout.MODE_FIXED);
        tab_nearby.setSelectedTabIndicatorHeight(0);
    }

    private void initMap() {
        mLocationManager = new LocationManager(this) ;
        mAddrSearchManager = new AddrSearchManager(this) ;
        if (null == mMap) {
            mMap = map_nearby.getMap();
        }
        // 定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle() ;
        // 拖动地图后定位小蓝点不返回到屏幕的中心位置
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次
        mMap.setOnMyLocationChangeListener(this);
        mMap.setMyLocationStyle(myLocationStyle);
        mMap.setMyLocationEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setMyLocationButtonEnabled(false);
        mLocationManager.startLocation();
        // poi 搜索类型
        searchType = new String[]{"05", "10", "2003", "1507", "1509"};
    }

    private void initRecyclerView() {
        ViewUtil.initRecycleView(this, rv_nearby);
        mNearbyAdapter = new ScenicNearbyAdapter(R.layout.item_scenic_nearby_view) ;
        rv_nearby.setAdapter(mNearbyAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tab_nearby.addOnTabSelectedListener(this);
        mNearbyAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        showContent(true);
        mAddrSearchManager.getSearch("", searchType[0], mCity, mLatLonPoint, this);
    }

    @Override
    protected String[] getPermissionRequest() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        return permissions;
    }

    /*设置标记*/
    private void setMarkerOptions(String name, int distance, double latitude, double longitude) {
        //在地图上添加一个marker，并将地图中移动至此处
        MarkerOptions mk = new MarkerOptions();
        //设置定位的图片 (默认)
        //本地图片BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable
        // .location_marker))
        mk.icon(BitmapDescriptorFactory.defaultMarker());
        //设置点击的名称
        mk.title(name);
        //点标记的内容
        mk.snippet("距离" + distance + "m");
        //点标记是否可拖拽
        //mk.draggable(true);
        //点标记的锚点
        mk.anchor(1.5f, 3.5f);
        //点的透明度
        //mk.alpha(0.7f);
        //设置marker平贴地图效果
        mk.setFlat(true);
        //设置纬度和经度
        LatLng ll = new LatLng(latitude, longitude);
        mk.position(ll);
        //清除所有marker等，保留自身
        mMap.clear(true);
        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newCameraPosition(new CameraPosition(ll, 16, 0, 0));
        mMap.animateCamera(cameraUpdate);
        mMap.addMarker(mk);
    }

    @Override
    public void onResume() {
        super.onResume();
        map_nearby.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map_nearby.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != map_nearby) {
            map_nearby.onDestroy();
            map_nearby = null;
        }
        mMap = null;
        mLocationManager.stopLocation();
    }

    // tab listener
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        mAddrSearchManager.getSearch("", searchType[position], mCity, mLatLonPoint, this);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    // poi
    @Override
    public void onSearched(List<PoiItem> poiItemList) {
        if (null != poiItemList && poiItemList.size() > 0) {
            mNearbyAdapter.setNewData(poiItemList);
        }
    }

    // my location
    @Override
    public void onMyLocationChange(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude) ;
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    // item click
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<PoiItem> data = mNearbyAdapter.getData();
        //设置点击的标记
        setMarkerOptions(data.get(position).getTitle(), data.get
                (position).getDistance(), data.get(position).getLatLonPoint()
                .getLatitude(), data.get(position).getLatLonPoint().getLongitude());
    }
}

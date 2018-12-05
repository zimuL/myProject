package com.zimu.my2018.quyoulib.location;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.inject.Inject;

import dagger.Module;

/**
 * 功能：获取当前地理位置
 * 描述：
 * Created by hxl on 2018/10/13
 */
@Module
public class LocationManager implements AMapLocationListener {

    public AMapLocationClient mLocationClient;   //声明mlocationClient对象
    public AMapLocationClientOption mLocationOption;//声明mLocationOption对象
    private HashMap<String, Object> mObjectHashMap = null;
    private CallBack callBack;

    @Inject
    public LocationManager(Context context) {
        mLocationClient = new AMapLocationClient(context) ;
        mLocationOption = new AMapLocationClientOption() ;// 初始化定位参数
        mLocationOption.setNeedAddress(true); // 设置返回地址信息
        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mLocationOption.setGpsFirst(true);
        mLocationClient.setLocationListener(this); // 定位监听
        mLocationClient.setLocationOption(mLocationOption);
    }

    /**
     * 开始搜索
     */
    public void startLocation() {
        mLocationClient.startLocation();
        Log.e("Location", "startLocation()");
    }

    /**
     * 停止搜索
     */
    public void stopLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
            this.callBack = null;
        }
    }

    public void getLocation(CallBack callBack) {
        this.callBack = callBack;
        if (mObjectHashMap != null) {
            callBack.onGetLocation(mObjectHashMap);
            this.callBack = null;
            return;
        }
        startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                if (mObjectHashMap == null) {
                    mObjectHashMap = new HashMap<>();
                }
                //定位成功回调信息，设置相关消息
                mObjectHashMap.put("locationType", aMapLocation.getLocationType());//获取当前定位结果来源，如网络定位结果，详见定位类型表
                mObjectHashMap.put("latitude", aMapLocation.getLatitude());//获取纬度
                mObjectHashMap.put("longitude", aMapLocation.getLongitude());//获取经度
                mObjectHashMap.put("accuracy", aMapLocation.getAccuracy());//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date date = new Date(aMapLocation.getTime());
                mObjectHashMap.put("updateTime", df.format(date));//定位时间
                mObjectHashMap.put("address", aMapLocation.getAddress());//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                mObjectHashMap.put("country", aMapLocation.getCountry());//国家信息
                mObjectHashMap.put("province", aMapLocation.getProvince());//省信息
                mObjectHashMap.put("city", aMapLocation.getCity());//城市信息
                mObjectHashMap.put("district", aMapLocation.getDistrict());//城区信息
                mObjectHashMap.put("street", aMapLocation.getStreet());//街道信息
                mObjectHashMap.put("streetNum", aMapLocation.getStreetNum());//街道门牌号信息
                mObjectHashMap.put("cityCode", aMapLocation.getCityCode());//城市编码
                mObjectHashMap.put("adCode", aMapLocation.getAdCode());//地区编码
                mLocationClient.stopLocation();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AMapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }

        if (callBack != null) {
            callBack.onGetLocation(mObjectHashMap);
            callBack = null;
        }
    }

    public interface CallBack {
        void onGetLocation(HashMap<String, Object> mObjectHashMap);
    }
}

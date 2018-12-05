package com.zimu.my2018.quyoulib.core.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zimu.my2018.quyoulib.core.base.interfaces.IBaseChooseInterface;
import com.zimu.my2018.quyoulib.core.base.interfaces.PermissionCallBack;
import com.zimu.my2018.quyoulib.core.base.interfaces.ViewClickCallBack;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 功能：无标题无刷新封装
 * 描述：
 * Created by hxl on 2018/10/4
 */
public abstract class BaseHcActivity extends DaggerAppCompatActivity implements ViewClickCallBack{

    public static final int TIME_INTERVAL = 1000;

    protected long mLastClickTime = 0; // 防治重复点击
    private Unbinder mBinder;
    private boolean registerEventBus = false;

    private IBaseChooseInterface mIBaseChooseInterface ;

    private View contentViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam(); // 初始化参数
        initBeforeAddView(); // 加载view前设置窗口
        setContentView(getContentView());
        mBinder = ButterKnife.bind(this);
        initSavedInstanceState(savedInstanceState);
        initBaseChoose();
        initView(); // 初始化设置
        initExtraData();
        initAppPresenter();
        initListener(); // 初始化加载信息
        loadData(); // 加载数据
        activityRequestPermissions();
    }

    public void initSavedInstanceState(Bundle savedInstanceState) {
    }

    @Override
    public void onViewClick(View view) {

    }

    protected void initParam(){

    }

    private void initBaseChoose() {
        mIBaseChooseInterface = getChooseManage();
    }

    protected IBaseChooseInterface getChooseManage() {
        return null;
    }

    protected void initBeforeAddView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
    }

    protected void initBeforeWhiteView() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
            window.setNavigationBarColor(Color.WHITE);
        }
    }

    protected void setFitSystemWindow(boolean fitSystemWindow) {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content))
                    .getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(fitSystemWindow);
    }

    public void hcFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            super.finish();
        }
    }

    protected View getContentView() {
        return LayoutInflater.from(this).inflate(getContentViewId(), null);

    }

    protected abstract int getContentViewId();

    protected abstract void initView();

    // 额外的数据处理，返回键监听
    protected void initExtraData() {}

    protected void initListener() {

    }

    protected abstract void loadData();

    // 初始化presenter
    protected void initAppPresenter() {
    }

    // 销毁presenter
    protected void destroyAppPresenter() {
    }

    private void activityRequestPermissions() {
        String[] strings = getPermissionRequest();
        if (strings != null && strings.length > 0) {
            checkPermissions(strings, () -> hasRequestPermissions());
        }
    }

    public void checkPermissions(String[] string, PermissionCallBack permissionCallBack ) {
        RxPermissions rxPermissions = new RxPermissions(this) ;
        rxPermissions.request(string)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            showOpenPermissionDialog(BaseHcActivity.this);
                        } else {
                            if (permissionCallBack != null) {
                                permissionCallBack.onHasPermission();
                            }
                        }
                    }
                });
    }

    protected String[] getPermissionRequest() {
        return null;
    }

    protected void hasRequestPermissions() {

    }

    protected void showOpenPermissionDialog(Context context) {

    }

    /**
     * 检查2秒内重复点击
     */
    protected boolean checkClickTime() {
        boolean isClick = false;
        if (System.currentTimeMillis() - mLastClickTime > TIME_INTERVAL) {
            mLastClickTime = System.currentTimeMillis();
            isClick = true;
        }
        return isClick;
    }

    /**
     * 注册EventBus
     */
    protected void registerEventBus() {
        registerEventBus = true;
        EventBus.getDefault().register(this);
    }

    protected void setViewListener(View view, ViewClickCallBack viewClickCallBack ) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        viewClickCallBack.onViewClick(view);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mIBaseChooseInterface != null) {
            mIBaseChooseInterface.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
        destroyAppPresenter();
        if (registerEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }
}

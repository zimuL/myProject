package com.zimu.my2018.quyoulib.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zimu.my2018.quyoulib.core.base.interfaces.IBaseChooseInterface;
import com.zimu.my2018.quyoulib.core.base.interfaces.ViewClickCallBack;
import com.zimu.my2018.quyoulib.utils.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import io.reactivex.functions.Consumer;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public abstract class BaseZimuFragment extends DaggerFragment implements ViewClickCallBack {

    public static final int TIME_INTERVAL = 2000;
    protected long mLastClickTime = 0; // 防止重复点击

    protected View self;
    private IBaseChooseInterface mIBaseChooseInterface ;
    private Unbinder mBinder;
    private boolean registerEventBus = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = this.getLayoutView(inflater, container);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        mBinder = ButterKnife.bind(this, self);
        initSavedInstanceState(savedInstanceState);
        return this.self;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBaseChoose();
        initParams();
        initView(view);
        initAppPresenter();
        initListener();
        loadData();
    }

    private View getLayoutView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    public void initSavedInstanceState(Bundle savedInstanceState) {

    }

    protected abstract int getLayoutId();

    protected abstract void initParams();

    protected void initView(View view) {

    }

    protected void initListener() {

    }

    protected void loadData() {

    }

    protected void showToast(String message) {
        ToastUtils.showCenter(getContext(), message);
    }

    protected void initAppPresenter() {

    }

    protected void destroyAppPresenter() {

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

    protected boolean checkClickTime() {
        boolean isClick = false;
        if (System.currentTimeMillis() - mLastClickTime >= TIME_INTERVAL) {
            mLastClickTime = System.currentTimeMillis();
            isClick = true;
        }
        return isClick;
    }

    private void initBaseChoose() {
        mIBaseChooseInterface = getChooseManager();
    }

    protected IBaseChooseInterface getChooseManager() {
        return null;
    }

    protected void registerEventBus() {
        registerEventBus = true;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onViewClick(View view) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mIBaseChooseInterface != null) {
            mIBaseChooseInterface.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
        destroyAppPresenter();
        if (registerEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }
}

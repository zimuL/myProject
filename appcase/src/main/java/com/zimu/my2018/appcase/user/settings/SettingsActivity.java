package com.zimu.my2018.appcase.user.settings;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：
 * 描述：settings
 * Created by hxl on 2018/10/27
 */
public class SettingsActivity extends BaseTitleActivity implements SettingsContract.View{

    @BindView(R2.id.rl_clear_cache)
    RelativeLayout rl_clear_cache;
    @BindView(R2.id.tv_cache)
    TextView tv_cache;
    @BindView(R2.id.tv_feedback)
    TextView tv_feedback;
    @BindView(R2.id.tv_about)
    TextView tv_about;
    @BindView(R2.id.tv_logout)
    TextView tv_logout;

    @Inject
    SettingsPresenter mPresenter;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
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
        return R.layout.activity_settings_view;
    }

    @Override
    protected void initView() {
        setTitle("设置");
    }

    @Override
    protected void initListener() {
        super.initListener();
        setViewListener(rl_clear_cache, this);
        setViewListener(tv_feedback, this);
        setViewListener(tv_about, this);
        setViewListener(tv_logout, this);
    }

    @Override
    protected void loadData() {
        showContent(true);
        loadCacheSize();
    }

    //设置缓存大小
    private void loadCacheSize() {
        try {
            String cacheSize = DeviceUtils.getTotalCacheSize(this);
            tv_cache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
        int id = view.getId();
        if (id == R.id.rl_clear_cache) {
            DeviceUtils.clearAllCache(this);
            loadCacheSize();
        } else if (id == R.id.tv_feedback) {

        } else if (id == R.id.tv_about) {

        } else if (id == R.id.tv_logout) {

        }
    }
}

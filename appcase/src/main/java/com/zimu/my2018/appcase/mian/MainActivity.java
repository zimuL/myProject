package com.zimu.my2018.appcase.mian;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.mian.fragment_community.CommunityFragment;
import com.zimu.my2018.appcase.mian.fragment_main.MainFragment;
import com.zimu.my2018.appcase.mian.fragment_mine.MineFragment;
import com.zimu.my2018.core.widget.bottom.BottomWidget;
import com.zimu.my2018.core.widget.data.BottomItemData;
import com.zimu.my2018.quyoulib.core.base.BaseZimuActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseZimuActivity implements MainContract.View, BottomWidget.TabSelectClick {

    @BindView(R2.id.bottom_widget)
    BottomWidget bottom_widget;

    @Inject
    MainFragment mMainFragment;
    @Inject
    CommunityFragment mCommunityFragment;
    @Inject
    MineFragment mMineFragment;

    @Inject
    protected MainPresenter mPresenter ;

    private FragmentManager fManager;

    private long firstTime = 0;
    private int lastSelectedPosition = 0;

    @Override
    protected void initBeforeAddView() {
        super.initBeforeAddView();
        // 设置透明通知栏
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main_view;
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
    protected void initView() {
        fManager = getSupportFragmentManager();
        InitBottomWidget();
    }

    private void InitBottomWidget() {
        bottom_widget.addBottomItems(new BottomItemData(R.drawable.tab_menu_mian, "首页"));
        bottom_widget.addBottomItems(new BottomItemData(R.drawable.tab_menu_community, "溜社区"));
        bottom_widget.addBottomItems(new BottomItemData(R.drawable.tab_menu_person, "我的"));
        bottom_widget.setTabSelectClick(this);
        bottom_widget.setFirstSelectedPosition(lastSelectedPosition);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected String[] getPermissionRequest() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        return permissions;
    }

    // 切换界面跳转
    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_content, fragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    // 隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mMainFragment != null) fragmentTransaction.hide(mMainFragment);
        if (mCommunityFragment != null) fragmentTransaction.hide(mCommunityFragment);
        if (mMineFragment != null) fragmentTransaction.hide(mMineFragment);

    }

    @Override
    public void onTabSelect(int index) {
        switch (index) {
            case 0:
                changeFragment(mMainFragment);
                lastSelectedPosition = 0;
                break;
            case 1:
                changeFragment(mCommunityFragment);
                lastSelectedPosition = 1;
                break;
            case 2:
                changeFragment(mMineFragment);
                lastSelectedPosition = 2;
                break;
        }
    }

    // 按2次返回键退出

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {  //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {    //两次按键小于2秒时，退出应用
                    finish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}

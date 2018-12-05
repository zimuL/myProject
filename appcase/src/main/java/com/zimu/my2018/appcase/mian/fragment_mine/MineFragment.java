package com.zimu.my2018.appcase.mian.fragment_mine;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.login.loginapp.LoginActivity;
import com.zimu.my2018.appcase.scenic.scenicattentionlist.ScenicAttentionListActivity;
import com.zimu.my2018.appcase.scenic.scenicevaluatelist.ScenicEvaluateListActivity;
import com.zimu.my2018.appcase.scenic.scenicfootprint.ScenicFootprintActivity;
import com.zimu.my2018.appcase.user.functionintroduce.FunctionIntroduceActivity;
import com.zimu.my2018.appcase.user.settings.SettingsActivity;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.login.UserBean;
import com.zimu.my2018.quyoulib.core.base.BaseZimuFragment;
import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;
import com.zimu.my2018.quyoulib.pref_shared_preferences.PrefManager;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.config.ConfigPackage;
import com.zimu.my2018.quyouui.core.constant.AliPicSuffix;
import com.zimu.my2018.quyouui.widget.fresco.FrescoDraweeView;

import javax.inject.Inject;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
@PerActivity
public class MineFragment extends BaseZimuFragment implements MineFragContract.View{

    @BindView(R2.id.rl_my_phone)
    RelativeLayout rl_my_phone;
    @BindView(R2.id.img_header_center)
    FrescoDraweeView img_header_center;
    @BindView(R2.id.tv_mine_name)
    TextView tv_mine_name;
    @BindView(R2.id.tv_mine_setting)
    TextView tv_mine_setting;
    @BindView(R2.id.tv_mine_evaluate)
    TextView tv_mine_evaluate;
    @BindView(R2.id.tv_mine_footprint)
    TextView tv_mine_footprint;
    @BindView(R2.id.tv_mine_attention)
    TextView tv_mine_attention;
    @BindView(R2.id.tv_mine_function)
    TextView tv_mine_function;
    @BindView(R2.id.tv_mine_service)
    TextView tv_mine_service;
    @BindView(R2.id.tv_mine_time)
    TextView tv_mine_time;
    @BindView(R2.id.tv_login)
    TextView tv_login;

    @Inject
    MineFragPresenter mPresenter;

    @Inject
    PrefManager mPrefManager ;

    private String mNickname;
    private String mUserUrl;
    private int mUserId;

    @Inject
    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_view;
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
    }

    @Override
    protected void initListener() {
        super.initListener();
        setViewListener(img_header_center, this);
        setViewListener(tv_mine_setting, this);
        setViewListener(tv_mine_evaluate, this);
        setViewListener(tv_mine_footprint, this);
        setViewListener(tv_mine_attention, this);
        setViewListener(tv_mine_function, this);
        setViewListener(tv_mine_service, this);
        setViewListener(tv_mine_time, this);
        setViewListener(tv_login, this);
    }

    @Override
    protected void loadData() {
        super.loadData();
        setInfo();
    }

    private void setInfo() {
        mUserId = mPrefManager.getInt("userId", 0);
        mNickname = mPrefManager.getString("nickname", "小溜趣游");
        mUserUrl = mPrefManager.getString("userUrl", "");
        setLoginState();
    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
        int id = view.getId();
        if (id == R.id.img_header_center) {
            showToast("我的");
        } else if (id == R.id.tv_mine_setting) {
            startActivity(SettingsActivity.getDiyIntent(getContext()));
        } else if (id == R.id.tv_mine_evaluate) {
            startActivity(ScenicEvaluateListActivity.getDiyIntent(getContext()));
        } else if (id == R.id.tv_mine_footprint) {
            startActivity(ScenicFootprintActivity.getDiyIntent(getContext()));
        } else if (id == R.id.tv_mine_attention) {
            startActivity(ScenicAttentionListActivity.getDiyIntent(getContext()));
        } else if (id == R.id.tv_mine_function) {
            startActivity(FunctionIntroduceActivity.getDiyIntent(getContext()));
        } else if (id == R.id.tv_mine_service) {
            showToast("联系客服");
        } else if (id == R.id.tv_mine_time) {
            showToast("小溜时光");
        } else if (id == R.id.tv_login) {
            startActivityForResult(LoginActivity.getDiyIntent(getContext()), 0x0001);
        }
    }

    private void setLoginState() {
        if (StringUtils.checkNullPoint(mNickname)) {
            tv_login.setVisibility(View.GONE);
            rl_my_phone.setVisibility(View.VISIBLE);
            tv_mine_name.setText(mNickname);
        }
        if (StringUtils.checkNullPoint(mUserUrl)) {
            String url = ScenicType.baseUrl + mUserUrl;
            img_header_center.setImageURI(Uri.parse(url + AliPicSuffix.getWTypePicSuffix(200)));
        } else {
            img_header_center.setImageURI(ConfigPackage.PACKAGE_NAME + R.mipmap.ic_mine_photo);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x0001 && resultCode == RESULT_OK) {
            if (null != data) {
                UserBean userBean = (UserBean) data.getSerializableExtra("user");
                mNickname = userBean.getNickname();
                mUserUrl = userBean.getUser_icon_url();
                mUserId = userBean.getUser_id();
                setLoginState();
            }
        }
    }
}

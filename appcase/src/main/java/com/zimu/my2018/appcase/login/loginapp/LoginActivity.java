package com.zimu.my2018.appcase.login.loginapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.login.registerapp.RegisterActivity;
import com.zimu.my2018.quyouapi.data.login.LoginData;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：
 * 描述：login
 * Created by hxl on 2018/11/6
 */
public class LoginActivity extends BaseTitleActivity implements LoginContract.View{

    @BindView(R2.id.et_login_name)
    EditText et_login_name;
    @BindView(R2.id.et_login_pwd)
    EditText et_login_pwd;
    @BindView(R2.id.tv_forget_pwd)
    TextView tv_forget_pwd;
    @BindView(R2.id.tv_login)
    TextView tv_login;
    @BindView(R2.id.tv_register)
    TextView tv_register;

    @Inject
    LoginPresenter mLoginPresenter ;

    private String encryptPwd;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void initAppPresenter() {
        super.initAppPresenter();
        mLoginPresenter.attachView(this);
    }

    @Override
    protected void destroyAppPresenter() {
        super.destroyAppPresenter();
        mLoginPresenter.detachView();
    }

    @Override
    protected int getAppView() {
        return R.layout.activity_login_view;
    }

    @Override
    protected void initView() {
        setTitle("登录");
    }

    @Override
    protected void initListener() {
        super.initListener();
        setViewListener(tv_login, this);
        setViewListener(tv_register, this);
    }

    @Override
    protected void loadData() {
        showContent(true);
    }

    // login
    private void login() {
        String name = et_login_name.getText().toString().trim();
        String pwd = et_login_pwd.getText().toString().trim();
        if (!StringUtils.checkNullPoint(name)) {
            showToast("请输入用户名");
            return;
        }
        if (!StringUtils.checkNullPoint(pwd)) {
            showToast("请输入密码");
            return;
        }
        if (StringUtils.checkNullPoint(encryptPwd)) {
            mLoginPresenter.login(name, encryptPwd);
        } else {
            mLoginPresenter.login(name, pwd);
        }

    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
        int id = view.getId();
        if (id == R.id.tv_login) {
            login();
        } else if (id == R.id.tv_register) {
            startActivityForResult(RegisterActivity.getDiyIntent(this), 0x0001);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x0001 && resultCode == RESULT_OK) {
            if (null != data) {
                Long phone = data.getLongExtra("phone", 0);
                encryptPwd = data.getStringExtra("encryptPwd");
                String pwd = data.getStringExtra("pwd");
                et_login_name.setText(phone + "");
                et_login_pwd.setText(pwd);
            }
        }
    }

    @Override
    public void onLoginSuccess(LoginData loginData) {
        showToast("登陆成功！");
        Intent intent = new Intent() ;
        intent.putExtra("user", loginData.getUser());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onLoginFailed(String msg) {
        showToast("登录失败！");
        Log.e("hxl", "onLoginFailed: " + msg );
    }
}

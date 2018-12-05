package com.zimu.my2018.appcase.login.registerapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.quyouapi.data.login.RegisterData;
import com.zimu.my2018.quyouapi.data.login.UserBean;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：
 * 描述：register
 * Created by hxl on 2018/11/7
 */
public class RegisterActivity extends BaseTitleActivity implements RegisterContract.View{

    @BindView(R2.id.et_register_name)
    EditText et_register_name;
    @BindView(R2.id.et_register_code)
    EditText et_register_code;
    @BindView(R2.id.tv_code)
    TextView tv_code;
    @BindView(R2.id.et_register_pwd)
    EditText et_register_pwd;
    @BindView(R2.id.et_register_pwd_again)
    EditText et_register_pwd_again;
    @BindView(R2.id.tv_register)
    TextView tv_register;
    @BindView(R2.id.tv_login)
    TextView tv_login;

    @Inject
    RegisterPresenter mPresenter;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
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
        return R.layout.activity_register_view;
    }

    @Override
    protected void initView() {
        setTitle("注册");
    }

    @Override
    protected void initListener() {
        super.initListener();
        setViewListener(tv_code, this);
        setViewListener(tv_register, this);
        setViewListener(tv_login, this);
    }

    @Override
    protected void loadData() {
        showContent(true);
    }

    private void register() {
        String name = et_register_name.getText().toString().trim();
        String pwd = et_register_pwd.getText().toString().trim();
        String pwd_again = et_register_pwd_again.getText().toString().trim();
        if (!StringUtils.checkNullPoint(name)) {
            showToast("请输入用户名");
            return;
        }
        if (!StringUtils.checkNullPoint(pwd)) {
            showToast("请输入密码");
            return;
        }
        if (!pwd.equals(pwd_again)) {
            showToast("两次输入的密码不同！");
            return;
        }
        mPresenter.register(name, pwd);
    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
        int id = view.getId();
        if (id == R.id.tv_code) {

        } else if(id == R.id.tv_register) {
            register();
        } else if (id == R.id.tv_login) {
            finish();
        }
    }

    @Override
    public void onRegisterSuccess(RegisterData response) {
        if (null != response) {
            int type = response.getType();
            if (1 == type) {
                Intent intent = new Intent() ;
                UserBean user = response.getUser();
                String pwd = et_register_pwd.getText().toString();
                long phone_number = user.getPhone_number();
                String user_password = user.getUser_password();
                intent.putExtra("phone", phone_number);
                intent.putExtra("encryptPwd", user_password);
                intent.putExtra("pwd", pwd);
                setResult(RESULT_OK, intent);
                finish();
                showToast("注册成功！");
            } else if (2 == type || 3 == type) {
                showToast("手机号不正确，请重试！");
            } else {
                showToast("注册失败！");
            }
        }
    }

    @Override
    public void onRegisterFailed(String msg) {
        showToast("注册失败！");
        Log.e("hxl", "onRegisterFailed: " + msg);
    }
}

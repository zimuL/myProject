package com.zimu.my2018.xiaoliuquyou.app.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.zimu.my2018.appcase.mian.MainActivity;
import com.zimu.my2018.quyoulib.core.base.BaseZimuActivity;
import com.zimu.my2018.quyoulib.utils.AppUtils;
import com.zimu.my2018.xiaoliuquyou.R;

/**
 * 启动页面
 */
public class SplashActivity extends BaseZimuActivity {

    private static final int MSG_FIRST_ENTER = 0x110; //第一次登陆或者更新后登陆
    private static final int MSG_SECOND_ENTER = 0x111; //非第一种情况
    private static final int SEND_MSG_DELAY_TIME = 2000; //延迟跳转时间

    private int versionCode;
    private int preVersionCode;
    private String key = "";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent mIntent = new Intent() ;
            switch (msg.what) {
                case MSG_FIRST_ENTER:
                    mIntent.setClass(SplashActivity.this,MainActivity.class);
                    break;
            }
            startActivity(mIntent);
            finish();
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void loadData() {
        versionCode = AppUtils.getVersionCode(this);
        Message message = new Message() ;
        message.what = MSG_FIRST_ENTER;
        message.arg1 = versionCode;
        mHandler.sendMessageDelayed(message, SEND_MSG_DELAY_TIME);
    }
}

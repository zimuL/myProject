package com.zimu.my2018.quyoulib.core.base.interfaces;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public interface IBaseChooseInterface {

    <T extends Serializable> void gotoChoose(Context context, int action, int chooseType,
                                             T t,ChooseCallBack chooseCallBack );

    <T> void onActivityResult(int requestCode, int resultCode, Intent data);
}

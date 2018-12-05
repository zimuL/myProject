package com.zimu.my2018.quyouui.utils;

import android.content.Context;

import com.zimu.my2018.quyouui.widget.dialog.CustomConfirmDialog;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class BaseDialogUtils {
    /**
     * 获取确认的dialog
     *
     * @param context
     * @param title
     * @param content
     * @return
     */
    public static CustomConfirmDialog getConfirmDialog(Context context, String title, String content) {
        return getConfirmDialog(context, title, null, content);
    }

    public static CustomConfirmDialog getConfirmDialog(Context context, String title, String confirmText, String content) {
        CustomConfirmDialog confirmDialog = new CustomConfirmDialog(context);
        confirmDialog.setTitle(title);
        confirmDialog.setContent(content);
        confirmDialog.setConfirmtext(confirmText);
        return confirmDialog;
    }
}

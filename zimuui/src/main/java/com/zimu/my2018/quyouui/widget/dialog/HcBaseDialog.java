package com.zimu.my2018.quyouui.widget.dialog;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.Window;
import android.view.WindowManager;

import com.zimu.my2018.quyouui.R;

/**
 * showDialog() 显示dialog
 * dismiss()  dismiss dialog
 * backgroundLight(double light) 弹出时背景亮度 值为0.0~1.0    1.0表示全黑  0.0表示全白
 * fromBottomToMiddle() 从底部一直弹到中间
 * fromBottom() 从底部弹出 显示在底部
 * fromLeftToMiddle() 从左边一直弹到中间退出也是到左边
 * fromRightToMiddle() 从右边一直弹到中间退出也是到右边
 * fromTop() 从顶部弹出 从顶部弹出  保持在顶部
 * fromTopToMiddle() 从顶部谈到中间  从顶部弹出  保持在中间
 * showDialog( int style) 显示一个Dialog自定义一个弹出方式  具体怎么写 可以模仿上面的
 * showDialog(boolean isAnimation) 如果为true 就显示默认的一个缩放动画
 * fullScreen() 全屏显示
 * fullWidth() 全屏宽度
 * fullHeight() 全屏高度
 * setWidthAndHeight(int width, int height) 自定义宽高
 * setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) 当dialog弹出是 按键的点击事件会被dialog获取
 * setDialogDismissListener(OnDismissListener listener) 设置dismiss监听
 * setOnCancelListener(OnCancelListener listener) 设置取消监听
 * setCancelAble(boolean cancel) 设置能否被取消
 * setCanceledOnTouchOutside(boolean cancel) 设置点击其他地方能否被取消
 */
public abstract class HcBaseDialog {
    protected Context mContext;
    protected int mDimColor = Color.BLACK;
    View Group;
    private Dialog mDialog;
    private Window mDialogWindow;

    public HcBaseDialog(Context context, int layoutId) {
        this(context, layoutId, false);
    }

    public HcBaseDialog(Context context, int layoutId, boolean isTranslucent) {
        mContext = context;
        DialogViewHolder dialogVh = DialogViewHolder.get(context, layoutId);
        View rootView = dialogVh.getConvertView();
        mDialog = new Dialog(context, R.style.dialog);
        mDialog.setContentView(rootView);
        mDialogWindow = mDialog.getWindow();
        if (isTranslucent) {
            backgroundLight(0.0);
        }
        convert(dialogVh);
    }

    public static AlertDialog.Builder creatNormalDialogBuilder(Context context, String title, String message) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message);
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

    /**
     * B
     * 把弹出框view holder传出去
     */
    public abstract void convert(DialogViewHolder holder);

    /**
     * 显示dialog
     */
    public HcBaseDialog showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    /**
     * @param light 弹出时背景亮度 值为0.0~1.0    1.0表示全黑  0.0表示全白
     * @return
     */
    public HcBaseDialog backgroundLight(double light) {
        if (light < 0.0 || light > 1.0)
            return this;
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.dimAmount = (float) light;
        mDialogWindow.setAttributes(lp);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public HcBaseDialog applyDim(ViewGroup dimView) {
        ViewGroup parent = dimView;
        Group = dimView;
        Drawable dim = new ColorDrawable(Color.parseColor("#808080"));//Color.parseColor("#f0eff5")
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * 0.8f));
        ViewOverlay overlay = parent.getOverlay();
        overlay.add(dim);
        return this;
    }

    /**
     * 清除背景变暗
     */

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void clearDim() {
        ViewOverlay overlay = Group.getOverlay();
        overlay.clear();
    }

    /**
     * 从底部一直弹到中间
     */
    @SuppressLint("NewApi")
    public HcBaseDialog fromBottomToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_bottom_in_bottom_out);
        return this;
    }

    /**
     * 从底部弹出
     */
    public HcBaseDialog fromBottom() {
        fromBottomToMiddle();
        mDialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        return this;
    }

    /**
     * 从左边一直弹到中间退出也是到左边
     */
    public HcBaseDialog fromLeftToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_left_in_left_out);
        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mDialogWindow.setGravity(Gravity.CENTER | Gravity.LEFT);
        return this;
    }

    /**
     * 从右边一直弹到中间退出也是到右边
     *
     * @return
     */
    public HcBaseDialog fromRightToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_right_in_right_out);
        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mDialogWindow.setGravity(Gravity.RIGHT);
        return this;
    }

    /**
     * 从顶部弹出 从顶部弹出  保持在顶部
     *
     * @return
     */
    public HcBaseDialog fromTop() {
        fromTopToMiddle();
        mDialogWindow.setGravity(Gravity.CENTER | Gravity.TOP);
        return this;
    }

    /**
     * 从顶部弹出 从顶部弹出  标题栏下
     *
     * @return
     */
    public HcBaseDialog fromTopBelowTitle(int height) {
        fromTopToMiddle();
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.y = height;
        mDialogWindow.setAttributes(lp);
        mDialogWindow.setGravity(Gravity.TOP);
        return this;
    }

    /**
     * 从顶部谈到中间  从顶部弹出  保持在中间
     *
     * @return
     */
    public HcBaseDialog fromTopToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_top_in_top_out);
        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return this;
    }

    /**
     * @param style 显示一个Dialog自定义一个弹出方式  具体怎么写 可以模仿上面的
     * @return
     */
    public HcBaseDialog showDialog(@StyleRes int style) {
        mDialogWindow.setWindowAnimations(style);
        mDialog.show();
        return this;
    }

    /**
     * @param isAnimation 如果为true 就显示默认的一个缩放动画
     * @return
     */
    public HcBaseDialog showDialog(boolean isAnimation) {
        mDialogWindow.setWindowAnimations(R.style.dialog_scale_anim_style);
        mDialog.show();
        return this;
    }

    /**
     * 全屏显示
     */
    public HcBaseDialog fullScreen() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }


    public HcBaseDialog setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        mDialog.setOnKeyListener(onKeyListener);
        return this;
    }

    /**
     * 全屏宽度
     */
    public HcBaseDialog fullWidth() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * 全屏高度
     */
    public HcBaseDialog fullHeight() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * @param width  自定义的宽度
     * @param height 自定义的高度
     * @return
     */
    public HcBaseDialog setWidthAndHeight(int width, int height) {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = width;
        wl.height = height;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * cancel dialog
     */
    public void cancelDialog() {
        if (mDialog != null && mDialog.isShowing())
            dismiss();
    }

    /**
     * cancel dialog
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


    /**
     * 设置监听
     */
    public HcBaseDialog setDialogDismissListener(DialogInterface.OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
        return this;
    }

    /**
     * 设置监听
     */
    public HcBaseDialog setOnCancelListener(DialogInterface.OnCancelListener listener) {
        mDialog.setOnCancelListener(listener);
        return this;
    }

    /**
     * 设置是否能取消
     */
    public HcBaseDialog setCancelAble(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }


    /**
     * 设置触摸其他地方是否能取消
     */
    public HcBaseDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

}


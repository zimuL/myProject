package com.zimu.my2018.quyouui.widget.fresco;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class FrescoDraweeView extends SimpleDraweeView {

    public FrescoDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public FrescoDraweeView(Context context) {
        super(context);
    }

    public FrescoDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrescoDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FrescoDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 设置图片
     */
    public void setImage(String path) {

    }

}

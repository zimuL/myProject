package com.zimu.my2018.quyouui.utils;

import android.content.Context;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class ImageLoadViewUtil {

    /**
     * 初始化ImageLoader加载器
     *
     * @param context
     */
    public static void init(Context context) {
        FrescoUtilImpl.init(context);
        GlideUtilImpl.init(context);
    }


    /**
     * 普通加载网络图片
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(String url, ImageView imageView) {
        if (imageView instanceof SimpleDraweeView) {
            FrescoUtilImpl.loadImage(url, (SimpleDraweeView) imageView);
        } else {
            GlideUtilImpl.loadImage(url, imageView);
        }
    }


    /**
     * 加载ResId资源O
     *
     * @param resId
     * @param imageView
     */
    public static void loadImageRes(int resId, ImageView imageView) {
        if (imageView instanceof SimpleDraweeView) {
            FrescoUtilImpl.loadImageRes(resId, (SimpleDraweeView) imageView);
        } else {
            GlideUtilImpl.loadImageRes(resId, imageView);
        }
    }

    /**
     * 加载文件路径
     *
     * @param filePath
     * @param imageView
     */
    public static void loadImageFile(String filePath, ImageView imageView) {
        if (imageView instanceof SimpleDraweeView) {
            FrescoUtilImpl.loadImageFile(filePath, (SimpleDraweeView) imageView);
        } else {
            GlideUtilImpl.loadImageFile(filePath, imageView);
        }
    }


}


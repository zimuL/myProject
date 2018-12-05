package com.zimu.my2018.quyouui.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zimu.my2018.quyouui.R;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class GlideUtilImpl {

    public final static int TYPE_NORMOL = 0;
    public final static int TYPE_CIRCLE = 1;
    public final static int TYPE_ROUNDEDCORNERS = 2;


    public static void init(Context context) {

    }

    /**
     * Glide 记载图片
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(String url, ImageView imageView) {
        Log.e("GlideUtilImpl", "loadImage url:" + url);
        displayUrl(url, imageView, R.mipmap.icon_pic_default_bg, 0);
    }

    /**
     * @param resId
     * @param imageView
     */
    public static void loadImageRes(int resId, ImageView imageView) {
        displayRes(resId, imageView, R.mipmap.icon_pic_default_bg, 0);
    }


    public static void loadImageFile(String filePath, ImageView imageView) {
        displayUrl(filePath, imageView, R.mipmap.icon_pic_default_bg, 0);
    }


    public static void displayUrl(String url, ImageView imageView, @DrawableRes int defaultImage, int showType) {
        // 不能崩
        if (imageView == null) {
            return;
        }

        Context context = imageView.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(defaultImage)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            switch (showType) {
                case TYPE_CIRCLE:
                    requestOptions.circleCrop();
                    break;
                case TYPE_ROUNDEDCORNERS:
                    requestOptions.transform(new RoundedCorners(4));
                    break;
                default:
                    break;
            }

            Glide.with(context)
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView)
                    .getSize((width, height) -> {
                        if (!imageView.isShown()) {
                            imageView.setVisibility(View.VISIBLE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void displayRes(@DrawableRes int resId, ImageView imageView, @DrawableRes int defaultImage, int showType) {
        // 不能崩
        if (imageView == null) {
            return;
        }

        Context context = imageView.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(defaultImage)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            switch (showType) {
                case TYPE_CIRCLE:
                    requestOptions.circleCrop();
                    break;
                case TYPE_ROUNDEDCORNERS:
                    requestOptions.transform(new RoundedCorners(4));
                    break;
                default:
                    break;
            }

            Glide.with(context)
                    .load(resId)
                    .apply(requestOptions)
                    .into(imageView)
                    .getSize((width, height) -> {
                        if (!imageView.isShown()) {
                            imageView.setVisibility(View.VISIBLE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

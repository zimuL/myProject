package com.zimu.my2018.quyouui.utils;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.zimu.my2018.quyouui.config.ConfigPackage;
import com.zimu.my2018.quyouui.config.ImagePipelineConfigFactory;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class FrescoUtilImpl {

    public static void init(Context context) {
        ImagePipelineConfig config = ImagePipelineConfigFactory.getImagePipelineConfig(context);
        Fresco.initialize(context, config);
    }

    public static void loadImage(String url, SimpleDraweeView imageView) {
        imageView.setImageURI(Uri.parse(url));
    }

    public static void loadImageRes(int resId, SimpleDraweeView imageView) {
        imageView.setImageURI(Uri.parse(ConfigPackage.PACKAGE_NAME + resId));
    }

    public static void loadImageFile(String filePath, SimpleDraweeView imageView) {
    }
}


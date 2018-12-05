package com.zimu.my2018.appcase.scenic.scenicbannerpicture.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayImageBean;
import com.zimu.my2018.quyoulib.utils.StringUtils;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/27
 */
public class ScenicBannerPictureAdapter extends BaseQuickAdapter<ArrayImageBean, BaseViewHolder> {

    public ScenicBannerPictureAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArrayImageBean item) {
        SimpleDraweeView draweeView = helper.getView(R.id.sd_image);

        String url = item.getResource_url();

        if (StringUtils.checkNullPoint(url)) {
            draweeView.setImageURI(Uri.parse(ScenicType.baseUrl + url));
        } else {
            draweeView.setImageURI(Uri.EMPTY);
        }
    }
}

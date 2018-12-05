package com.zimu.my2018.appcase.scenic.scenicdetail.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.scenic.scenicdetail.ServicePointsBean;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.constant.AliPicSuffix;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/9
 */
public class ScenicDetailAdapter extends BaseQuickAdapter<ServicePointsBean, BaseViewHolder> {
    public ScenicDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServicePointsBean item) {
        SimpleDraweeView draweeView = helper.getView(R.id.img_scenic_view);
        String url = item.getResource_url();
        String name = item.getService_name();
        if (StringUtils.checkNullPoint(url)) {
            if (url.lastIndexOf(".") != -1) {
                url = url.replace(".", ScenicType.thumbnail + ".");
            }
            draweeView.setImageURI(Uri.parse(ScenicType.baseUrl + url + AliPicSuffix.getWTypePicSuffix(200)));
        } else {
            draweeView.setImageURI(Uri.EMPTY);
        }
        helper.setText(R.id.tv_scenic_name, name);
    }
}

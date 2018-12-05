package com.zimu.my2018.appcase.mian.fragment_main.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyouapi.data.main.ScenicWeekendListBean;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.constant.AliPicSuffix;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/6
 */
public class ScenicWeekendAdapter extends BaseQuickAdapter<ScenicWeekendListBean, BaseViewHolder> {


    public ScenicWeekendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicWeekendListBean item) {
        SimpleDraweeView draweeView = helper.getView(R.id.sd_image);

        String url = item.getUrl();
        String name = item.getName();

        if (StringUtils.checkNullPoint(url)) {
            draweeView.setImageURI(Uri.parse(url + AliPicSuffix.getWTypePicSuffix(200)));
        }

        helper.setText(R.id.tv_scenic_name, name);
    }
}

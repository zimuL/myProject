package com.zimu.my2018.appcase.scenic.scenicdetail.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicsBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicnear.NearByscenicsBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicnear.ScenicDetailNearByData;
import com.zimu.my2018.quyoulib.utils.StringUtils;

import java.util.List;
import java.util.Locale;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/9
 */
public class ScenicDetailNearbyAdapter extends BaseQuickAdapter<NearByscenicsBean, BaseViewHolder> {
    public ScenicDetailNearbyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearByscenicsBean item) {
        SimpleDraweeView draweeView = helper.getView(R.id.img_scenic_view);
        ScenicBean scenic = item.getScenic();
        String url = scenic.getCover_image();
        String scenicName = scenic.getScenic_name();
        String distanceByUser = item.getDistanceByUser();
        if (StringUtils.checkNullPoint(url)) {
            draweeView.setImageURI(Uri.parse(ScenicType.baseUrl + url));
        } else {
            draweeView.setImageURI(Uri.EMPTY);
        }
        helper.setText(R.id.tv_scenic_name, scenicName);
        helper.setText(R.id.tv_scenic_distance, String.format(Locale.getDefault(), "%sm", distanceByUser));
    }
}

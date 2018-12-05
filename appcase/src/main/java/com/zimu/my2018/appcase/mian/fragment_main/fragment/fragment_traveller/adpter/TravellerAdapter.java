package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_traveller.adpter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyouapi.data.main.ScenicTravellerListData;
import com.zimu.my2018.quyoulib.utils.StringUtils;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/11
 */
public class TravellerAdapter extends BaseQuickAdapter<ScenicTravellerListData, BaseViewHolder>{


    public TravellerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicTravellerListData item) {
        SimpleDraweeView img_header = helper.getView(R.id.img_header);
        String headerUrl = item.getHeaderUrl();
        if (StringUtils.checkNullPoint(headerUrl)) {
            img_header.setImageURI(Uri.parse(headerUrl));
        } else {
            img_header.setImageURI(Uri.EMPTY);
        }
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_brief, item.getBrief());
    }
}

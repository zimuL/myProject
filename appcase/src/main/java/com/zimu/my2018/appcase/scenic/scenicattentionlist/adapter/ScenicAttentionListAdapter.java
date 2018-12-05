package com.zimu.my2018.appcase.scenic.scenicattentionlist.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttentionListData;
import com.zimu.my2018.quyoulib.utils.StringUtils;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ScenicAttentionListAdapter extends BaseQuickAdapter<ScenicAttentionListData, BaseViewHolder> {

    public ScenicAttentionListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicAttentionListData item) {
        SimpleDraweeView draweeView = helper.getView(R.id.sd_image);
        ScenicBean scenic = item.getScenic();
        String cover_image = scenic.getCover_image();
        String scenic_name = scenic.getScenic_name();
        String address = scenic.getAddress();
        if (StringUtils.checkNullPoint(cover_image)) {
            draweeView.setImageURI(Uri.parse(ScenicType.baseUrl + cover_image));
        }
        helper.setText(R.id.tv_scenic_attention_name, scenic_name);
        helper.setText(R.id.tv_scenic_attention_location, address);

        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.ll_main);
    }
}

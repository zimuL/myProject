package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_footprint.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyouapi.data.main.ScenicFootPrintChooseListData;
import com.zimu.my2018.quyoulib.utils.StringUtils;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/10
 */
public class FootPrintChooseAdapter extends BaseQuickAdapter<ScenicFootPrintChooseListData,
        BaseViewHolder> {
    public FootPrintChooseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicFootPrintChooseListData item) {
        SimpleDraweeView img_header = helper.getView(R.id.img_header) ;
        SimpleDraweeView img_content = helper.getView(R.id.img_content) ;
        String headerUrl = item.getHeaderUrl();
        if (StringUtils.checkNullPoint(headerUrl)) {
            img_header.setImageURI(Uri.parse(headerUrl));
        } else {
            img_header.setImageURI(Uri.EMPTY);
        }
        String name = item.getName();
        helper.setText(R.id.tv_name, name);
        String contentUrl = item.getContentUrl();
        if (StringUtils.checkNullPoint(contentUrl)) {
            img_content.setImageURI(Uri.parse(contentUrl));
        } else {
            img_content.setImageURI(Uri.EMPTY);
        }
        String brief = item.getBrief();
        helper.setText(R.id.tv_brief, brief);
    }
}

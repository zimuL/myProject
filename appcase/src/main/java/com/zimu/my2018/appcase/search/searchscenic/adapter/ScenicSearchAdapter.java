package com.zimu.my2018.appcase.search.searchscenic.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicsBean;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/24
 */
public class ScenicSearchAdapter extends BaseQuickAdapter<ScenicsBean, BaseViewHolder> {

    public ScenicSearchAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicsBean item) {
        ScenicBean scenic = item.getScenic();
        String scenicName = scenic.getScenic_name();
        helper.setText(R.id.tv_scenic_name, scenicName);
    }
}

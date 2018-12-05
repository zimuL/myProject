package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_scenic.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayImageBean;
import com.zimu.my2018.quyouapi.data.main.scenics.HeatTemperatureBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicsBean;
import com.zimu.my2018.quyoulib.utils.NumberUtil;
import com.zimu.my2018.quyoulib.utils.StringUtils;

import java.util.List;

/**
 * Created by hxl on 2018/10/7 at haiChou.
 */
public class MainScenicAdapter extends BaseQuickAdapter<ScenicsBean, BaseViewHolder> {

    public MainScenicAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicsBean item) {
        SimpleDraweeView draweeView = helper.getView(R.id.img_scenic_view);
        ScenicBean scenic = item.getScenic();
        String scenic_name = scenic.getScenic_name();
        String url = scenic.getCover_image();
        String distance = item.getDistanceByUser();
        HeatTemperatureBean heatTemperature = item.getHeatTemperature();
        int lookCount = heatTemperature.getLook_over_count();
        String lookNum = NumberUtil.getTenThousandData(lookCount);
        double aDouble = Double.parseDouble(distance);
        String distanceS = NumberUtil.getKm(aDouble);
        if (StringUtils.checkNullPoint(url)) {
            draweeView.setImageURI(Uri.parse(ScenicType.baseUrl + url));
        }
        helper.setText(R.id.tv_scenic_name, scenic_name);
        helper.setText(R.id.tv_scenic_distance, distanceS);
        helper.setText(R.id.tv_scenic_people,String.format("%s人次", lookNum));
    }
}

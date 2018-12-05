package com.zimu.my2018.appcase.mian.fragment_main.viewholder;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouapi.data.main.banner.MainBannerData;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayImageBean;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.constant.AliPicSuffix;

import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/6
 */
public class BannerViewHolder implements Holder<MainBannerData.DataBean>{

    SimpleDraweeView mDraweeView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_banner_page_view, null, false);
        mDraweeView = view.findViewById(R.id.img_bg_view);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, MainBannerData.DataBean data) {
        String resource_url = ScenicType.bannerUrl + data.getUrl();
        if (StringUtils.checkNullPoint(resource_url)) {
            mDraweeView.setImageURI(Uri.parse(resource_url + AliPicSuffix.getWTypePicSuffix(400)));
        } else {
            mDraweeView.setImageURI(Uri.EMPTY);
        }
    }
}

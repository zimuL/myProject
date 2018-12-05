package com.zimu.my2018.quyouui.widget.pic.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.R;
import com.zimu.my2018.quyouui.config.ConfigPackage;
import com.zimu.my2018.quyouui.widget.data.photo.NetPhotoData;

import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public class PicHorizontalAdapter extends BaseQuickAdapter<NetPhotoData, BaseViewHolder> {
    private Context mContext;
    private boolean showDesc = true;
    private int textSize;
    private int textColor;
    private int picSize;
    private int countNum;
    private int paddingWidth;
    private int innerPaddingWidth;

    public PicHorizontalAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<NetPhotoData> data) {
        super(layoutResId, data);
        this.mContext = context;
        initView();
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    private void initView() {
        textSize = 12;
        Resources resources = mContext.getResources();
        textColor = resources.getColor(R.color.color_66);
        paddingWidth = resources.getDimensionPixelSize(R.dimen.dp_15);
        innerPaddingWidth = resources.getDimensionPixelSize(R.dimen.dp_10);
        picSize = DeviceUtils.dip2px(mContext, 60);
    }

    @Override
    protected void convert(BaseViewHolder helper, NetPhotoData item) {
        LinearLayout root_view = helper.getView(R.id.root_view);

        SimpleDraweeView pic = helper.getView(R.id.img_art);
        TextView desc = helper.getView(R.id.img_desc);

        if (showDesc) {
            desc.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = desc.getLayoutParams();
            layoutParams.width = picSize;
            desc.setLayoutParams(layoutParams);
            desc.setTextSize(textSize);
            desc.setTextColor(textColor);
            desc.setText(StringUtils.getNotNullStr(item.getPicDes()));
        } else {
            desc.setVisibility(View.GONE);
        }

        String url = StringUtils.isEmpty(item.getLocalPath()) ? item.getNetPath() : getLocalUrl(item.getLocalPath());
        if (StringUtils.checkNullPoint(url)) {
            pic.setImageURI(Uri.parse(url));
        }

        if (countNum > 0) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) root_view.getLayoutParams();
            params.width = (DeviceUtils.deviceWidth(mContext) - 2 * (paddingWidth) - (countNum - 1) * innerPaddingWidth) / countNum;
            root_view.setLayoutParams(params);
        }

    }

    /**
     * 根据localPath获取加载的本地路径
     *
     * @param localPath
     * @return
     */
    private String getLocalUrl(String localPath) {
        if (localPath.startsWith(ConfigPackage.PACKAGE_NAME)) {
            return localPath;
        } else {
            return "file://" + localPath;
        }
    }

    public void setShowDesc(boolean showDesc) {
        this.showDesc = showDesc;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setPicSize(int picSize) {
        this.picSize = picSize;
    }

}

package com.zimu.my2018.quyouui.widget.pic;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyouui.R;
import com.zimu.my2018.quyouui.config.ConfigPackage;
import com.zimu.my2018.quyouui.widget.data.photo.NetPhotoData;
import com.zimu.my2018.quyouui.widget.pic.adapter.PicHorizontalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public class PicHorizontalEditView extends LinearLayout {

    private Context mContext;

    private boolean _showDesc = true;
    private boolean _addDefault = true;
    private int _direction, _spanCount, _imgSize;

    private PicHorizontalAdapter adapter;
    private List<NetPhotoData> list = new ArrayList<>();

    private OptionListener listener;

    public PicHorizontalEditView(Context context) {
        super(context);
        initView(context, null);
    }

    public PicHorizontalEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public PicHorizontalEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        initDefaultAttrs();
        initAttrs(context, attrs);
        initList();
        addView(getRecyclerView(context));
    }

    @NonNull
    private RecyclerView getRecyclerView(Context context) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        if (_direction == 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, _spanCount));
        }


        adapter = new PicHorizontalAdapter(mContext, R.layout.item_pic_horizontal_view, list);
        adapter.setCountNum(_spanCount);
        adapter.setShowDesc(_showDesc);
        adapter.setPicSize(_imgSize);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            if (listener == null)
                return;

            if (_addDefault && position == 0) {
                listener.addPic();
            } else {
                listener.itemClick(_addDefault ? position - 1 : position, (NetPhotoData) adapter1.getData().get(position));
            }
        });
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    /**
     * 初始化默认参数
     */
    private void initDefaultAttrs() {
        _showDesc = true;
        _addDefault = true;
        _spanCount = 4;
        _direction = 0;
        _imgSize = DeviceUtils.dip2px(mContext, 60);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PicDescConView);
        final int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.PicDescConView_pdc_show_desc) {
            _showDesc = typedArray.getBoolean(attr, _showDesc);
        } else if (attr == R.styleable.PicDescConView_pdc_show_choose) {
            _addDefault = typedArray.getBoolean(attr, _addDefault);
        } else if (attr == R.styleable.PicDescConView_pdc_direction) {
            _direction = typedArray.getInt(attr, _direction);
        } else if (attr == R.styleable.PicDescConView_pdc_grid_count) {
            _spanCount = typedArray.getInteger(attr, _spanCount);
        } else if (attr == R.styleable.PicDescConView_pdc_img_size) {
            _imgSize = (int) typedArray.getDimension(attr, _imgSize);
        }
    }

    public void setData(List<NetPhotoData> list) {
        initList();
        this.list.addAll(list);
        adapter.setNewData(this.list);
    }

    public void addData(List<NetPhotoData> list) {
        adapter.addData(list);
    }

    public void addData(NetPhotoData netPicInfo) {
        adapter.addData(netPicInfo);
    }

    /**
     * 图片预览被删除以后 来更新view
     *
     * @param urls
     */
    public void refreshData(List<String> urls) {
        List<NetPhotoData> tmpList = new ArrayList<>();
        int length = list.size();
        int start = _addDefault ? 1 : 0;
        NetPhotoData netPicInfo;
        for (; start < length; start++) {
            netPicInfo = list.get(start);
            if (!urls.contains(netPicInfo.getLocalPath()) && !urls.contains(netPicInfo.getNetPath())) {
                tmpList.add(netPicInfo);
            }
        }

        int index;
        for (NetPhotoData picInfo : tmpList) {
            index = list.indexOf(picInfo);
            adapter.remove(index);
        }
    }

    private void initList() {
        list.clear();
        if (_addDefault) {
            list.add(getDefaultNetPic());
        }
    }

    @NonNull
    private NetPhotoData getDefaultNetPic() {
        NetPhotoData netPicInfo = new NetPhotoData();
        netPicInfo.setLocalPath(ConfigPackage.PACKAGE_NAME + R.mipmap.icon_camer);
        netPicInfo.setPicDes("添加");
        return netPicInfo;
    }

    public List<NetPhotoData> getList() {
        List<NetPhotoData> tmpList = new ArrayList<>();
        tmpList.addAll(list);
        if (_addDefault) {
            tmpList.remove(0);
        }
        return tmpList;
    }

    public void setListener(OptionListener listener) {
        this.listener = listener;
    }

    public interface OptionListener {
        void addPic();

        void itemClick(int position, NetPhotoData netPicInfo);
    }
}

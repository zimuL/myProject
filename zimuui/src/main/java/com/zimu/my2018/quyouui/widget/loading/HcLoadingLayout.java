package com.zimu.my2018.quyouui.widget.loading;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zimu.my2018.quyouui.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class HcLoadingLayout extends FrameLayout {

    private LayoutInflater mInflater;

    private Map<Integer, View> mLayouts = new HashMap<>(); //loadinglayout 里面的view

    private int mContentId = NO_ID;                        //自己的ViewID
    private int mLoadingResId = NO_ID;                     //加载loadingViewID   最前面的ViewID
    private int mPreLoadingResId = NO_ID;                  //加载预加载LoadingViewID
    private int mEmptyResId = NO_ID;                       //空布局ViewID
    private int mErrorResId = NO_ID;                       //出错布局ViewID

    public HcLoadingLayout(Context context) {
        this(context, null);
    }

    public HcLoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HcLoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.HcLoadingLayout);

        mPreLoadingResId = typedArray.getInt(R.styleable.HcLoadingLayout_pre_loading_res_id, LoadingConfig.getPreLoadingLayoutId());
        mLoadingResId = typedArray.getInt(R.styleable.HcLoadingLayout_loading_res_id, LoadingConfig.getLoadingLayoutId());
        mEmptyResId = typedArray.getInt(R.styleable.HcLoadingLayout_empty_res_id, LoadingConfig.getEmptyLayoutID());
        mErrorResId = typedArray.getInt(R.styleable.HcLoadingLayout_err_res_id, LoadingConfig.getErrorLayoutID());
        typedArray.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 0) {
            return;
        }
        if (getChildCount() > 1) {
            removeViews(1, getChildCount() - 1);
        }
        View view = getChildAt(0);
        setContentView(view);

        if (mErrorResId != NO_ID) {
            layout(mErrorResId);
        }
        if (mEmptyResId != NO_ID) {
            layout(mEmptyResId);
        }

        if (mPreLoadingResId != NO_ID) {
            layout(mPreLoadingResId);
        }
        if (mLoadingResId != NO_ID) {
            layout(mLoadingResId);
        }

        showPreLoading();
    }


    private void setContentView(View view) {
        mContentId = view.getId();
        mLayouts.put(mContentId, view);
    }

    public HcLoadingLayout setLoading(@LayoutRes int id) {
        if (mLoadingResId != id) {
            remove(mLoadingResId);
            mLoadingResId = id;
            layout(id);
        }
        return this;
    }


    public HcLoadingLayout setEmpty(@LayoutRes int id) {
        if (mEmptyResId != id) {
            remove(mEmptyResId);
            mEmptyResId = id;
            layout(id);
        }
        return this;
    }

    public HcLoadingLayout setPreLoading(@LayoutRes int id) {
        if (mPreLoadingResId != id) {
            remove(mPreLoadingResId);
            mPreLoadingResId = id;
            layout(id);
        }
        return this;
    }

    public HcLoadingLayout setError(@LayoutRes int id) {
        if (mErrorResId != id) {
            remove(mErrorResId);
            mErrorResId = id;
            layout(id);
        }
        return this;
    }

    private void remove(int layoutId) {
        if (mLayouts.containsKey(layoutId)) {
            View vg = mLayouts.remove(layoutId);
            removeView(vg);
        }
    }

    public void showLoading() {
        show(mLoadingResId);
    }


    public void showLoadingOff() {
        View view = mLayouts.get(mLoadingResId);
        if (view != null) {
            view.setVisibility(GONE);
        }
    }

    public void showPreLoading() {
        show(mPreLoadingResId);
    }

    public void showEmpty() {
        show(mEmptyResId);
    }

    public void showError() {
        show(mErrorResId);
    }

    public void showContent() {
        show(mContentId);
    }

    private void show(int layoutId) {
        if (layoutId == NO_ID) {
            return;
        }

        if (layoutId != mLoadingResId) {
            for (View view : mLayouts.values()) {
                view.setVisibility(GONE);
            }
        }
        layout(layoutId).setVisibility(VISIBLE);
    }


    private View layout(int layoutId) {
        if (mLayouts.containsKey(layoutId)) {
            return mLayouts.get(layoutId);
        }
        View layout = mInflater.inflate(layoutId, this, false);
        layout.setVisibility(GONE);
        addView(layout);
        mLayouts.put(layoutId, layout);
        return layout;
    }

    /**
     * 获取加载的View
     *
     * @param layoutId
     * @return
     */
    public View getView(int layoutId) {
        if (mLayouts.containsKey(layoutId)) {
            return mLayouts.get(layoutId);
        } else {
            return null;
        }
    }

}

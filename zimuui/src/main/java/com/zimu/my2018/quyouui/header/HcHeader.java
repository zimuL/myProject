package com.zimu.my2018.quyouui.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zimu.my2018.quyouui.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class HcHeader extends RelativeLayout implements RefreshHeader {
    private AnimationDrawable mAnimationDrawableArrow;
    private AnimationDrawable mAnimationDrawable;
    private AnimationDrawable mAnimationDrawableEnd;
    private ImageView mArrowView;//前面动画
    private ImageView mProgressView;//刷新动画视图
    private ImageView mEndArrowView;//后面动画

    public HcHeader(Context context) {
        super(context);
        initView(context);
    }

    public HcHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public HcHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    private void initView(Context context) {
        setGravity(Gravity.CENTER);

        mArrowView = new ImageView(context);
        mArrowView.setBackgroundResource(R.drawable.animation_hc_dropdown_frame);
        mAnimationDrawableArrow = (AnimationDrawable) mArrowView.getBackground();

        mProgressView = new ImageView(context);
        mProgressView.setBackgroundResource(R.drawable.animation_hc_loading_frame);
        mAnimationDrawable = (AnimationDrawable) mProgressView.getBackground();

        mEndArrowView = new ImageView(context);
        mEndArrowView.setBackgroundResource(R.drawable.animation_hc_loadingend_frame);
        mAnimationDrawableEnd = (AnimationDrawable) mEndArrowView.getBackground();

        addView(mArrowView, RelativeLayout.LayoutParams.MATCH_PARENT, DensityUtil.dp2px(60));
        addView(mProgressView, LayoutParams.MATCH_PARENT, DensityUtil.dp2px(60));
        addView(mEndArrowView, LayoutParams.MATCH_PARENT, DensityUtil.dp2px(60));
        setMinimumHeight(DensityUtil.dp2px(60));
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.FixedBehind;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        int index = (int) (percent * 43);
        if (index >= 43) {
            index = 42;
        }
        mAnimationDrawableArrow.selectDrawable(index);
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        mAnimationDrawable.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        mAnimationDrawable.stop();//停止动画
        mAnimationDrawableEnd.setOneShot(true);
        mAnimationDrawableEnd.start();
        return 750;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mArrowView.setVisibility(VISIBLE);
                mProgressView.setVisibility(GONE);//隐藏动画
                mEndArrowView.setVisibility(GONE);
                break;
            case Refreshing:
                mProgressView.setVisibility(VISIBLE);//显示加载动画
                mArrowView.setVisibility(GONE);
                mEndArrowView.setVisibility(GONE);
                break;
            case RefreshFinish:
                mProgressView.setVisibility(GONE);
                mArrowView.setVisibility(GONE);
                mEndArrowView.setVisibility(VISIBLE);
                break;
        }
    }
}


package com.zimu.my2018.quyouui.core.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zimu.my2018.quyoulib.core.base.BaseHcActivity;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyoulib.utils.ToastUtils;
import com.zimu.my2018.quyouui.R;
import com.zimu.my2018.quyouui.header.HcHeader;
import com.zimu.my2018.quyouui.utils.BaseDialogUtils;
import com.zimu.my2018.quyouui.widget.dialog.CustomConfirmDialog;
import com.zimu.my2018.quyouui.widget.loading.HcLoadingLayout;
import com.zimu.my2018.quyouui.widget.title.CustomTitle;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public abstract class BaseTitleActivity extends BaseHcActivity{

    private FrameLayout contact_layout;
    private CustomTitle mCustomTitle;
    private SmartRefreshLayout mRefreshLayout ;
    private HcLoadingLayout mHcLoadingLayout ;
    private CustomTitle.OnBackListener mOnBackListener ;

    private ImageView img_module;
    private TextView tv_module_title;
    private TextView tv_module_des;
    private TextView tv_module_commit;

    @Override
    protected View getContentView() {
        View view = null;
        ViewGroup lastViewGroup = null;
        if (bindTitle()) {
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(getTitleView());
            view = layout;
            lastViewGroup = layout;
        }

        if (bindRefresh()) {
            mRefreshLayout = new SmartRefreshLayout(this) ;
            mRefreshLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mRefreshLayout.setDragRate(1);
            mRefreshLayout.setEnableLoadMore(false);
            mRefreshLayout.setEnableOverScrollDrag(true);
            mRefreshLayout.setEnableOverScrollBounce(true);
            mRefreshLayout.setEnableNestedScroll(true);
            mRefreshLayout.setEnablePureScrollMode(true);
            mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
            mRefreshLayout.setBackgroundColor(Color.parseColor("#f1f7fd"));
            mRefreshLayout.setDisableContentWhenLoading(true);
            mRefreshLayout.setDisableContentWhenRefresh(true);
            if (isBlueHeader()) {
                mRefreshLayout.setBackgroundColor(Color.parseColor("#23a4ff"));
                mRefreshLayout.setEnableRefresh(false);
            }
            if (lastViewGroup != null) {
                lastViewGroup.addView(mRefreshLayout);
            }
            if (view == null) {
                view = mRefreshLayout;
            }
            lastViewGroup = mRefreshLayout;
        }

        if (bindLoadingLayout()) {
            View parentView = LayoutInflater.from(this)
                    .inflate(R.layout.activity_base_title_view, null);
            mHcLoadingLayout = parentView.findViewById(R.id.loading);
            initLoadingView(mHcLoadingLayout);
            contact_layout = parentView.findViewById(R.id.contact_layout);
            if (lastViewGroup != null) {
                lastViewGroup.addView(mHcLoadingLayout);
            }
            if (view == null) {
                view = parentView;
            }
            lastViewGroup = contact_layout;
        }
        View inflate = getContentViewFirst() != null ? getContentViewFirst()
                : LayoutInflater.from(this).inflate(getAppView(), null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (lastViewGroup != null) {
            lastViewGroup.addView(inflate);
        } else {
            view = inflate;
        }
        return view;
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected void initExtraData() {
        super.initExtraData();
        setOnBackListener(mOnBackListener);
    }

    // 获取title
    private View getTitleView() {
        mCustomTitle= new CustomTitle(this) ;
        mCustomTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return mCustomTitle;
    }

    private void initLoadingView(HcLoadingLayout hcLoadingLayout) {
        if (getPreLayoutID() != -1) {
            mHcLoadingLayout.setPreLoading(getPreLayoutID());
        }
        if (getEmptyID() != -1) {
            mHcLoadingLayout.setEmpty(getEmptyID());
        }

        if (getErrorID() != -1) {
            mHcLoadingLayout.setError(getErrorID());
        }
    }

    protected int getPreLayoutID() {
        return R.layout.view_empty_zfpz;
    }

    protected int getErrorID() {
        return R.layout.module_error_view;
    }

    protected int getEmptyID() {
        return R.layout.module_empty_view;
    }

    public HcLoadingLayout getLoading() {
        return mHcLoadingLayout;
    }

    /**
     * 初始化空布局
     */
    public void initEmptyView(int icon, String title,  String des, String commitTitle,
                              View.OnClickListener onClickListener) {
        View view = mHcLoadingLayout.getView(R.layout.module_empty_view);
        img_module = view.findViewById(R.id.img_module);
        tv_module_title = view.findViewById(R.id.tv_module_title);
        tv_module_des = view.findViewById(R.id.tv_module_des);
        tv_module_commit = view.findViewById(R.id.tv_module_commit);

        if (img_module != null) {
            if (icon != -1) {
                img_module.setImageResource(icon);
                img_module.setVisibility(View.VISIBLE);
            } else {
                img_module.setVisibility(View.GONE);
            }
        }

        if (title != null && tv_module_title != null) {
            tv_module_title.setText(title);
        }


        if (des != null && tv_module_des != null) {
            tv_module_des.setText(des);
        }

        if (tv_module_commit != null) {
            if (StringUtils.checkNullPoint(commitTitle)) {
                tv_module_commit.setText(commitTitle);
                tv_module_commit.setVisibility(View.VISIBLE);
            } else {
                tv_module_commit.setVisibility(View.GONE);
            }

            if (onClickListener != null) {
                tv_module_commit.setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * RecyclerView 的空白页面
     */
    public View getRecyclerEmptyView(int icon, String title, String des, String commitTitle, int Color, View.OnClickListener onClickListener) {
        View view = getLayoutInflater().inflate(R.layout.module_empty_view, null);
        RelativeLayout re_all = view.findViewById(R.id.Rl_all);
        img_module = view.findViewById(R.id.img_module);
        tv_module_title = view.findViewById(R.id.tv_module_title);
        tv_module_des = view.findViewById(R.id.tv_module_des);
        tv_module_commit = view.findViewById(R.id.tv_module_commit);

        if (Color != 0) {
            re_all.setBackgroundColor(Color);
        }
        if (img_module != null) {
            if (icon != -1) {
                img_module.setImageResource(icon);
                img_module.setVisibility(View.VISIBLE);
            } else {
                img_module.setVisibility(View.INVISIBLE);
            }
        }

        if (title != null && tv_module_title != null) {
            tv_module_title.setText(title);
        }


        if (des != null && tv_module_des != null) {
            tv_module_des.setText(des);
        }

        if (tv_module_commit != null) {
            if (StringUtils.checkNullPoint(commitTitle)) {
                tv_module_commit.setText(commitTitle);
                tv_module_commit.setVisibility(View.VISIBLE);
            } else {
                tv_module_commit.setVisibility(View.GONE);
            }

            if (onClickListener != null) {
                tv_module_commit.setOnClickListener(onClickListener);
            }
        }
        return view;
    }

    /**
     * 初始化Error界面数据
     */
    public void initErrorView(String commitTitle, View.OnClickListener onClickListener) {
        initErrorView(null, commitTitle, onClickListener);
    }

    private void initErrorView(String errorDes, String commitTitle, View.OnClickListener onClickListener) {
        View view = mHcLoadingLayout.getView(R.layout.module_error_view);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_error_des = view.findViewById(R.id.tv_error_des);
        if (StringUtils.checkNullPoint(errorDes)) {
            tv_error_des.setText(errorDes);
        }
        if (tv_commit != null) {
            if (StringUtils.checkNullPoint(commitTitle)) {
                tv_commit.setText(commitTitle);
                tv_commit.setVisibility(View.VISIBLE);
            } else {
                tv_commit.setVisibility(View.GONE);
            }

            if (onClickListener != null) {
                tv_commit.setOnClickListener(onClickListener);
            }
        }
    }

    protected boolean isBlueHeader() {
        return false;
    }

    // 优先使用此方法获取view
    protected View getContentViewFirst() {
        return null;
    }

    protected abstract int getAppView();

    // 是否有title
    protected boolean bindTitle() {
        return true;
    }

    // 是否设置refreshView
    protected boolean bindRefresh() {
        return true;
    }

    // 是否使用loadingLayout
    protected boolean bindLoadingLayout() {
        return true;
    }

    public void setRefreshLayoutHeader() {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshHeader(new HcHeader(this));
        }
    }

    //设置开启刷新加载
    public void setRefreshLoadMore(OnRefreshLoadMoreListener refreshLoadMore) {
        if (refreshLoadMore != null && mRefreshLayout != null) {
            mRefreshLayout.setEnablePureScrollMode(false);
            mRefreshLayout.setEnableOverScrollBounce(true);
            mRefreshLayout.setEnableAutoLoadMore(true);
            mRefreshLayout.setEnableRefresh(true);
            mRefreshLayout.setEnableLoadMore(true);
            mRefreshLayout.setEnableOverScrollDrag(true);
            mRefreshLayout.setOnRefreshLoadMoreListener(refreshLoadMore);
        }
    }

    //开启refreshLayout
    public void setRefreshLayoutVisble() {
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnablePureScrollMode(false);
            mRefreshLayout.setEnableOverScrollBounce(false);
            mRefreshLayout.setEnableAutoLoadMore(false);
            mRefreshLayout.setEnableRefresh(true);
            mRefreshLayout.setEnableLoadMore(false);
            mRefreshLayout.setEnableOverScrollDrag(true);
        }
    }

    //关闭refreshLayout
    public void setRefreshLayoutInVisble() {
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnablePureScrollMode(false);
            mRefreshLayout.setEnableOverScrollBounce(false);
            mRefreshLayout.setEnableAutoLoadMore(false);
            mRefreshLayout.setEnableRefresh(false);
            mRefreshLayout.setEnableLoadMore(false);
            mRefreshLayout.setEnableOverScrollDrag(false);
        }
    }

    //结束上拉加载
    public void finishLoadMore() {
        finishLoadMore(false);
    }

    public void finishLoadMore(boolean isOver) {
        if (mRefreshLayout != null) {
            if (isOver) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore();
            }
        }
    }


    //结束下拉刷新
    public void finishRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
        }
    }

    //设置返回监听 注意在复写 的initListener中回调
    public void setOnBackListener(CustomTitle.OnBackListener onClickListener) {
        if (mCustomTitle != null) {
            if (onClickListener != null) {
                this.mOnBackListener = onClickListener;
                mCustomTitle.setOnBackListener(onClickListener);
            } else {
                mCustomTitle.setOnBackListener(() -> hcFinish());
            }
        }
    }

    //显示内容
    public void showContent(boolean show) {
        if (mHcLoadingLayout != null) {
            if (show) {
                mHcLoadingLayout.showContent();
            } else {
                mHcLoadingLayout.showEmpty();
            }
        }
    }

    //显示empty
    public void showEmpty() {
        if (mHcLoadingLayout != null) {
            mHcLoadingLayout.showEmpty();
        }
    }

    //显示error
    public void showError() {
        if (mHcLoadingLayout != null) {
            mHcLoadingLayout.showError();
        }
    }

    //显示
    public void showPreLoad() {
        if (mHcLoadingLayout != null) {
            mHcLoadingLayout.showPreLoading();
        }
    }

    //显示Toast
    public void showToast(String msg) {
        ToastUtils.showCenter(this, msg);
    }

    /**
     * 返回键退出
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mOnBackListener != null) {
                    mOnBackListener.onBack();
                    return true;
                } else {
                    hcFinish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    //普通Title
    public void setTitle(String title) {
        if (mCustomTitle != null) {
            mCustomTitle.setTitle(title);
        }
    }

    public void setTitleBackGroudColor(int colorid) {
        if (mCustomTitle != null) {
            mCustomTitle.setBackGroudColor(colorid);
        }

    }

    //右边带文字
    public void setTitle(String title, String rightStr, View.OnClickListener onClickListener) {
        if (mCustomTitle != null) {
            mCustomTitle.setTitle(title);
            mCustomTitle.setRightTv(rightStr);
            mCustomTitle.set_type(CustomTitle.RIGHT_TEXT_TYPE);
            mCustomTitle.setRightClickListener(onClickListener);

        }
    }

    //右边带文字+图标
    public void setTitle(String title, String rightStr, int rightIconId, View.OnClickListener onClickListener) {
        if (mCustomTitle != null) {
            mCustomTitle.setTitle(title);
            mCustomTitle.setRightTv(rightStr);
            mCustomTitle.setRtvDr(rightIconId);
            mCustomTitle.set_type(CustomTitle.RIGHT_TEXT_TYPE);
            mCustomTitle.setRightClickListener(onClickListener);
        }
    }

    //右边带图标无文字
    public void setTitle(String title, int rightIconId, View.OnClickListener onClickListener) {
        if (mCustomTitle != null) {
            mCustomTitle.setTitle(title);
            mCustomTitle.setRightICon(rightIconId);
            mCustomTitle.set_type(CustomTitle.RIGHT_IMAGE_TYPE);
            mCustomTitle.setRightClickListener(onClickListener);
        }
    }

    //无中间标题,返回按钮有标题,右边带文字无图标
    public void setLeftTitle(String title, String rightStr, View.OnClickListener onClickListener) {
        if (mCustomTitle != null) {
            mCustomTitle.setBacktitle(title);
            mCustomTitle.setRightTv(rightStr);
            mCustomTitle.set_type(CustomTitle.RIGHT_TEXT_TYPE);
            mCustomTitle.setRightClickListener(onClickListener);
        }
    }

    //无中间标题,返回按钮有标题,无图无字
    public void setLeftTitle(String title) {
        if (mCustomTitle != null) {
            mCustomTitle.setBacktitle(title);
        }
    }

    //标题无返回按钮
    public void setNoBack() {
        if (mCustomTitle != null) {
            mCustomTitle.setShowBack(false);
        }
    }

    @Override
    protected void showOpenPermissionDialog(Context context) {
        super.showOpenPermissionDialog(context);
        CustomConfirmDialog checkDialog = BaseDialogUtils.getConfirmDialog(context, "权限检查",
                "该操作缺少相应权限，是否立刻去设置?");
        checkDialog.setListener(() -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

            Uri uri = Uri.fromParts("package", context.getPackageName(), null);

            intent.setData(uri);

            context.startActivity(intent);

        });
        checkDialog.show();
    }
}

package com.zimu.my2018.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyouui.widget.dividerdecoration.DividerDecoration;

import java.lang.reflect.Field;

/**
 * Created by hxl on 2018/10/7 at haiChou.
 */
public class ViewUtil {
    /**
     * View获取焦点
     *
     * @param view
     */
    static public void requestViewFoucus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /**
     * 初始化recyvleview
     *
     * @param recyclerview
     */
    public static void initRecycleView(Context context, RecyclerView recyclerview) {
        initRecycleView(context, recyclerview, new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    public static void initRecycleView(Context context, RecyclerView recyclerview, DividerDecoration itemDecoration) {
        initRecycleView(recyclerview, new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false), itemDecoration);
    }

    public static void initRecycleView(Context context, RecyclerView recyclerview, RecyclerView.LayoutManager layoutManager) {
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#f0eff5"), context.getResources().getDimensionPixelSize(R.dimen.dp_1), 0, 0);
        initRecycleView(recyclerview, layoutManager, itemDecoration);
    }

    public static void initRecycleView(RecyclerView recyclerview, RecyclerView.LayoutManager layoutManager, DividerDecoration itemDecoration) {
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addItemDecoration(itemDecoration);
    }


    //获取间隔
    public static DividerDecoration getDividerDecoration(Context mContext) {
        return getDividerDecoration(mContext, 1);
    }

    //获取间隔
    public static DividerDecoration getDividerDecoration(Context mContext, float jj) {
        return new DividerDecoration(Color.parseColor("#f0eff4"), DeviceUtils.dip2px(mContext, jj), 0, 0);
    }

    public static DividerDecoration getDividerDecoration(Context mContext, float jj, float pl, float pr) {
        return new DividerDecoration(Color.parseColor("#f0eff4"), DeviceUtils.dip2px(mContext, jj), DeviceUtils.dip2px(mContext, pl), DeviceUtils.dip2px(mContext, pr));
    }


    /**
     * 设置tab下面横向变短
     *
     * @param tabLayout
     */
    public static void setTabItemWidth(TabLayout tabLayout) {
        tabLayout.post(() -> {

            try {
                //拿到tabLayout的mTabStrip属性
                Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
                mTabStripField.setAccessible(true);

                LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);

                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);

                    int tabWidth = tabView.getWidth();

                    //拿到tabView的mTextView属性
                    Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                    mTextViewField.setAccessible(true);

                    TextView mTextView = (TextView) mTextViewField.get(tabView);

                    tabView.setPadding(0, 0, 0, 0);

                    //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                    int width = 0;
                    width = mTextView.getWidth();
                    if (width == 0) {
                        mTextView.measure(0, 0);
                        width = mTextView.getMeasuredWidth();
                    }

                    //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                    params.width = width;
                    params.leftMargin = (tabWidth - width) / 2;
                    params.rightMargin = (tabWidth - width) / 2;
                    tabView.setLayoutParams(params);

                    tabView.invalidate();
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        });
    }
}

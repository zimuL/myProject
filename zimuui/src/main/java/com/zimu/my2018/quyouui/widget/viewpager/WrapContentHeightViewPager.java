package com.zimu.my2018.quyouui.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 功能：解决viewPager+Fragment会出现viewpager中的fragment高度不正常的问题
 * 描述：
 * Created by hxl on 2018/10/9
 */
public class WrapContentHeightViewPager extends ViewPager {

    private int current;
    private int height = 0;
    // 保存position与对于的View
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<>();
    private boolean scrollable = true;

    public WrapContentHeightViewPager(Context context) {
        super(context);
    }

    public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mChildrenViews.size() > current) {
            View child = mChildrenViews.get(current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // ViewPager+Fragment ViewPager适应Fragment高度解决
    public void resetHeight(int current) {
        this.current = current;
        if (mChildrenViews.size() > current) {
            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams)
                    getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }

    // 保存position与对于的View
    public void setObjectForPosition(View view, int position) {
        mChildrenViews.put(position, view);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollable) {
            return true;
        }
        return super.onTouchEvent(ev);
    }



    public boolean isScrollble() {
        return scrollable;

    }


    public void setScrollble(boolean scrollble) {
        this.scrollable = scrollble;

    }


}


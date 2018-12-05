package com.zimu.my2018.core.widget.bottom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.core.widget.data.BottomItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class BottomWidget extends LinearLayout {

    private List<BottomItemData> mBottomItemData = new ArrayList<>() ;
    private List<View> mViews = new ArrayList<>() ;
    TabSelectClick mTabSelectClick;

    public BottomWidget(Context context) {
        this(context, null);
    }

    public BottomWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    /*初始化view*/
    private void initView(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    /*添加Item*/
    public void addBottomItems(BottomItemData bottomItemData) {
        mBottomItemData.add(bottomItemData);
        View view = null;
        ImageView tab_image;
        TextView tab_text;

        switch (bottomItemData.getType()) {
            case 0:
                view = LayoutInflater.from(getContext())
                        .inflate(R.layout.widget_bottom_item_view, null);
                tab_image = view.findViewById(R.id.tab_image);
                tab_text = view.findViewById(R.id.tab_title);
                tab_image.setBackgroundResource(bottomItemData.getIconResource());
                tab_text.setText(bottomItemData.getText());
                break;
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT) ;
        layoutParams.weight = 1;
        addView(view, layoutParams);
        mViews.add(view);
        view.setOnClickListener(v -> {
          if (null != mTabSelectClick) {
              int position = mViews.indexOf(v);
              BottomItemData itemData = mBottomItemData.get(position);
              if (itemData.getType() != 1) {
                  for (View view1 : mViews) {
                      view1.setSelected(false);
                  }
                  v.setSelected(true);
              }
              mTabSelectClick.onTabSelect(position);
          }
        });
    }

    /*获取view*/
    public View getViewByIndex(int index) {
        View view = null;
        if (null != mViews && mViews.size() > 0) {
            view = mViews.get(index);
        }
        return view;
    }

    public void setFirstSelectedPosition(int firstSelectedPosition) {
        View view = mViews.get(firstSelectedPosition);
        if (null !=mTabSelectClick) {
            BottomItemData bottomItemData = mBottomItemData.get(firstSelectedPosition);
            if (bottomItemData.getType() != 1) {
                for(View v: mViews) {
                    v.setSelected(false);
                }
                view.setSelected(true);
            }
            mTabSelectClick.onTabSelect(firstSelectedPosition);
        }
    }

    public void setTabSelectClick(TabSelectClick tabSelectClick) {
        mTabSelectClick = tabSelectClick;
    }

    public interface TabSelectClick {
        void onTabSelect(int index);
    }
}

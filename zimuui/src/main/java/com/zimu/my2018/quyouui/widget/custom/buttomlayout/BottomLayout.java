package com.zimu.my2018.quyouui.widget.custom.buttomlayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.R;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class BottomLayout extends LinearLayout {
    private View mView;
    private DisplayMetrics displayMetrics;
    private float lastX = 0;
    private float lastY = 0;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private boolean isFirst = true;
    private Context context;

    private int MINHEIGHT = 200;
    private TextView mTitleView;
    private TextView mContentView;

    private int bottomTop = 0;
    private int bottomBot = 0;
    private int maxBottom;

    private boolean mIsUpdate = false;
    private TextView mPageNum;
    private LinearLayout mOuterLayout;
    private int bottomTop2;
    private int bottomBot2;
    private Runnable mRun;
    private int lastTop = 0;
    private boolean onTouch = true;

    public BottomLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    /**
     * 初始化View
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        MINHEIGHT = dpToPixels(context, 100);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.bot_arr);

        String title = ta.getString(R.styleable.bot_arr_bot_title);
        String content = ta.getString(R.styleable.bot_arr_bot_content);
        ta.recycle();

        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.widget_bottom_layout_view, this, true);

        mTitleView = mView.findViewById(R.id.bot_title_text);
        mContentView = mView.findViewById(R.id.bot_content);
        mPageNum = mView.findViewById(R.id.page_num);

        mOuterLayout = mView.findViewById(R.id.outer_layout);
        mContentView.setText(content);
        //设置TextView可以滚动
//        mContentView.setMovementMethod(new ScrollingMovementMethod());
        mTitleView.setText(title);
        this.context = context;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTtitle(String title) {
        mTitleView.setText(title);
    }

    /**
     * 设置页数
     *
     * @param page
     */
    public void setPage(String page) {
        mPageNum.setText(page);
    }

    /**
     * 设置内容
     *
     * @param content
     */
    public void setContent(final String content) {
        mIsUpdate = true;
        if (StringUtils.checkNullPoint(content)) {
            mContentView.setText(content);
            mContentView.setVisibility(VISIBLE);
        } else {
            mContentView.setVisibility(GONE);
        }
        mTitleView.removeCallbacks(mRun);
        onTouch = false;
        //添加全局布局侦听器
        getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            if (mIsUpdate) {
                mIsUpdate = false;
                int height = mContentView.getHeight();
                if (mContentView.getVisibility() == GONE) {
                    height = 0;
                }
                int scHeight = height;
                bottomTop = getTop();
                bottomTop2 = mOuterLayout.getTop();
                bottomBot = getBottom();
                bottomBot2 = mOuterLayout.getBottom();

                maxBottom = getBottom();
                if (scHeight > pixelsToDp(context, 100)) {

                    bottomTop = getTop() + (height - MINHEIGHT);
                    bottomBot = getBottom() + (height - MINHEIGHT);

                    bottomTop2 = mOuterLayout.getTop() + (height - MINHEIGHT);
                    bottomBot2 = mOuterLayout.getBottom() + (height - MINHEIGHT);
                }
                mOuterLayout.layout(getLeft(), bottomTop2, getRight(), bottomBot2);

                mTitleView.postDelayed(mRun = () -> {
                    layout(getLeft(), bottomTop, getRight(), bottomBot);
                    mOuterLayout.layout(getLeft(), 0, getRight(), mOuterLayout.getHeight());
                    onTouch = true;
                }, 800);
            }
        });

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    /**
     * 分发事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (isFirst) {
            // 得到屏幕的宽
            displayMetrics = getResources().getDisplayMetrics();
            screenWidth = displayMetrics.widthPixels;
            // 得到标题栏和状态栏的高度
            Rect rect = new Rect();
            Window window = ((Activity) context).getWindow();
            int statusBarHeight = rect.top;
            int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
            int titleBarHeight = contentViewTop - statusBarHeight;
            // 得到屏幕的高
            screenHeight = displayMetrics.heightPixels - (statusBarHeight + titleBarHeight);
            isFirst = false;


        }

        if (!onTouch) {
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的距离
                float distanceX = event.getRawX() - lastX;
                float distanceY = event.getRawY() - lastY;
                //移动后控件的坐标
                left = (int) (getLeft() + distanceX);
                top = (int) (getTop() + distanceY);
                right = (int) (getRight() + distanceX);
                bottom = (int) (getBottom() + distanceY);
                //处理拖出屏幕的情况
                if (left < 0) {
                    left = 0;
                    right = getWidth();
                }
                if (right > screenWidth) {
                    right = screenWidth;
                    left = screenWidth - getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = getHeight();
                }
                if (bottom > bottomBot) {
                    bottom = bottomBot;
                    top = bottomBot - getHeight();
                }

                if (bottom < maxBottom) {
                    bottom = maxBottom;
                    top = maxBottom - getHeight();
                }
                //移动View
                layout(getLeft(), top, getRight(), bottom);
                lastX = event.getRawX();
                lastY = event.getRawY();

                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    //dp转px
    public int dpToPixels(Context c, int dp) {
        return (int) (c.getResources().getDisplayMetrics().density * dp);
    }

    //px转dp
    public int pixelsToDp(Context c, int pixel) {
        return (int) ((float) pixel / c.getResources().getDisplayMetrics().density);
    }

}

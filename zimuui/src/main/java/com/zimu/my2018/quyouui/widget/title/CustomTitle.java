package com.zimu.my2018.quyouui.widget.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.R;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class CustomTitle extends RelativeLayout {

    public static final int CUSTOM_TYPE = 0;
    public static final int RIGHT_TEXT_TYPE = 1;
    public static final int RIGHT_IMAGE_TYPE = 2;

    private TextView imgBack;
    private TextView tvTitleName;
    private TextView tvRight;
    private ImageView rightMenu;
    private RelativeLayout rightMenuCon;
    private RelativeLayout rl_root_widget;

    private int _type;
    private boolean _showBack;
    private int _rightICon;
    private String _title, _rightTv;
    private View rightView;
    private int _rtvDr;//right tv drawable right

    private OnBackListener onBackListener;
    private Context mContext;

    public CustomTitle(Context context) {
        super(context);
        initView(context, null);
    }

    public CustomTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    public void setRightClickListener(View.OnClickListener rightClickListener) {
        rightView.setOnClickListener(rightClickListener);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;

        View view = View.inflate(context, R.layout.weiget_title_view, this);
        initWidget(view);
        initDefaultAttrs();

        initAttrs(context, attrs);
        tvTitleName.setText(StringUtils.getNotNullStr(_title));
        refreshBackView(_showBack);
        refreshTitleTypeView(_type);
    }

    private void initWidget(View view) {
        imgBack = view.findViewById(R.id.img_back);
        tvTitleName = view.findViewById(R.id.tv_title_name);
        tvRight = view.findViewById(R.id.tv_right);
        rightMenu = view.findViewById(R.id.right_menu);
        rightMenuCon = view.findViewById(R.id.right_menu_con);
        rl_root_widget = view.findViewById(R.id.rl_root_widget);
    }


    //刷新返回button view
    private void refreshBackView(boolean showBack) {
        imgBack.setVisibility(showBack ? VISIBLE : GONE);
        imgBack.setOnClickListener(v -> onBackListener.onBack());
    }

    //刷新显示类型
    private void refreshTitleTypeView(int type) {
        switch (type) {
            case CUSTOM_TYPE:

                break;
            case RIGHT_TEXT_TYPE:
                rightView = tvRight;
                tvRight.setVisibility(VISIBLE);
                rightMenu.setVisibility(GONE);
                tvRight.setText(StringUtils.isEmpty(_rightTv) ? "保存" : _rightTv);
                if (_rtvDr != -1) {
                    Drawable drawable = getResources().getDrawable(_rtvDr);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvRight.setCompoundDrawables(null, null, drawable, null);
                }
                break;
            case RIGHT_IMAGE_TYPE:
                rightView = rightMenuCon;
                rightMenuCon.setVisibility(VISIBLE);
                rightMenu.setVisibility(VISIBLE);
                rightMenu.setImageResource(_rightICon);
                break;
        }
    }

    public void set_type(int type) {
        refreshTitleTypeView(type);
    }

    public void setTitle(String title) {
        tvTitleName.setText(title);
    }

    public void setBacktitle(String title) {
        imgBack.setText(title);
    }

    public void setShowBack(boolean showBack) {
        refreshBackView(showBack);
    }

    public void setRightICon(int rightICon) {
        this._rightICon = rightICon;
        rightMenu.setImageResource(rightICon);
    }

    public void setRightTv(String _rightTv) {
        this._rightTv = _rightTv;
        tvRight.setText(StringUtils.isEmpty(_rightTv) ? "保存" : _rightTv);
    }

    public void setRtvDr(int rtvDr) {
        Drawable drawable = getResources().getDrawable(rtvDr);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvRight.setCompoundDrawables(null, null, drawable, null);
    }

    private void initDefaultAttrs() {
        _showBack = true;
        _title = "";
        _rightTv = "";
        _type = 0;
        _rightICon = R.mipmap.icon_three_white_spot;
        _rtvDr = -1;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitle);

        if (typedArray == null)
            return;

        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.CustomTitle_ct_show_back) {
            _showBack = typedArray.getBoolean(attr, _showBack);
        } else if (attr == R.styleable.CustomTitle_ct_title_label) {
            _title = typedArray.getString(attr);
        } else if (attr == R.styleable.CustomTitle_ct_type) {
            _type = typedArray.getInteger(attr, _type);
        } else if (attr == R.styleable.CustomTitle_ct_right_icon) {
            _rightICon = typedArray.getResourceId(attr, _rightICon);
        } else if (attr == R.styleable.CustomTitle_ct_right_tv) {
            _rightTv = typedArray.getString(attr);
        } else if (attr == R.styleable.CustomTitle_ct_right_tv_dr) {
            _rtvDr = typedArray.getResourceId(attr, _rtvDr);
        }
    }

    public void setOnBackListener(OnBackListener onBackListener) {
        this.onBackListener = onBackListener;
    }

    public void setBackGroudColor(int colorid) {
        if (rl_root_widget != null) {
            rl_root_widget.setBackgroundColor(colorid);
        }
    }

    public interface OnBackListener {
        void onBack();
    }

}

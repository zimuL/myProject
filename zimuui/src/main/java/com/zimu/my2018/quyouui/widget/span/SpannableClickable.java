package com.zimu.my2018.quyouui.widget.span;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.zimu.my2018.quyouui.R;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public abstract class SpannableClickable extends ClickableSpan implements View.OnClickListener {

    /**
     * text颜色
     */
    private int textColor;

    public SpannableClickable(Context mContext) {
        int DEFAULT_COLOR_ID = R.color.color_8290AF;
        this.textColor = mContext.getResources().getColor(DEFAULT_COLOR_ID);
    }

    public SpannableClickable(Context mContext, int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        ds.setColor(textColor);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}

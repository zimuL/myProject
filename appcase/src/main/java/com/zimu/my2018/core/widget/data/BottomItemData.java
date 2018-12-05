package com.zimu.my2018.core.widget.data;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class BottomItemData {
    private int type; // 0 图文 1 图
    private int mIconResource;
    private String text;

    public BottomItemData(int mIconResource, String text) {
        type = 0;
        this.mIconResource = mIconResource;
        this.text = text;
    }

    public BottomItemData() {
        type = 1;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIconResource() {
        return mIconResource;
    }

    public void setIconResource(int iconResource) {
        mIconResource = iconResource;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.zimu.my2018.quyouui.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.R;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class CustomConfirmDialog extends HcBaseDialog {
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_confirm;

    private ConfrimListener listener;
    private ConfrimCancleListener cancleListener;

    public CustomConfirmDialog(Context context) {
        super(context, R.layout.dialog_confirm_custom);
    }

    @Override
    public void convert(DialogViewHolder holder) {
        tv_title = holder.getView(R.id.confirm_title);
        tv_content = holder.getView(R.id.confirm_content);
        tv_confirm = holder.getView(R.id.confirm_ok);


        holder.getView(R.id.confirm_cancel).setOnClickListener(view -> {
            if (cancleListener != null)
                cancleListener.cancle();
            dismiss();
        });

        holder.getView(R.id.confirm_ok).setOnClickListener(view -> {
            if (listener != null)
                listener.confim();
            dismiss();
        });

        tv_title.setVisibility(View.GONE);
    }

    public void show() {
        this.fullWidth().fromLeftToMiddle().showDialog();
    }


    /**
     * 内容着重提醒
     */
    public void setContentBold() {
        tv_content.setTextSize(18);
        tv_content.getPaint().setFakeBoldText(true);
    }

    public void setTitle(String title) {
        tv_title.setVisibility(View.GONE);
        tv_title.setText(StringUtils.getNotNullStr(title));
    }

    public void setContent(String content) {
        tv_content.setText(StringUtils.getNotNullStr(content));
    }

    public void setConfirmtext(String confirmtext) {
        tv_confirm.setText(StringUtils.isEmpty(confirmtext) ? "确认" : confirmtext);
    }


    public void setListener(ConfrimListener listener) {
        this.listener = listener;
    }

    public void setCancleListener(ConfrimCancleListener cancleListener) {
        this.cancleListener = cancleListener;
    }

    public interface ConfrimListener {
        void confim();
    }

    public interface ConfrimCancleListener {
        void cancle();
    }
}

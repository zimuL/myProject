package com.zimu.my2018.quyouui.core.base;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.quyouui.R;
import com.zimu.my2018.quyouui.widget.dividerdecoration.DividerDecoration;

import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/27
 */
public abstract class BaseRecycleViewActivity<T> extends BaseTitleActivity {

    protected RecyclerView recycle_view;

    protected BaseQuickAdapter adapter;

    @Override
    protected int getAppView() {
        return R.layout.activity_base_recycle_view;
    }

    protected abstract BaseQuickAdapter getQuickAdapter();

    @Override
    protected void initView() {
        recycle_view = findViewById(R.id.recycle_view);
        adapter = getQuickAdapter();
        RecyclerView.ItemDecoration itemDecoration = getDividerDecoration();
        if (itemDecoration != null) {
            recycle_view.addItemDecoration(itemDecoration);
        }
        recycle_view.setLayoutManager(getLayout());
        recycle_view.setAdapter(adapter);
        adapter.bindToRecyclerView(recycle_view);
        adapter.setOnItemClickListener((adapter1, view, position) -> onRecycleItemClick(adapter1, view, position));

        adapter.setOnItemChildClickListener((adapter12, view, position) -> onRecycleChildItemClick(adapter12, view, position));

    }

    protected void onBaseItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
    }

    protected void onRecycleChildItemClick(BaseQuickAdapter adapter12, View view, int position) {
    }

    protected void onRecycleItemClick(BaseQuickAdapter adapter1, View view, int position) {

    }

    @NonNull
    protected RecyclerView.LayoutManager getLayout() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @NonNull
    protected RecyclerView.ItemDecoration getDividerDecoration() {
        return new DividerDecoration(Color.parseColor("#f0eff5"), getResources().getDimensionPixelSize(R.dimen.dp_1), 0, 0);
    }


    public void setNewData(List<T> lists) {
        adapter.setNewData(lists);
    }

    public void addData(List<T> lists) {
        adapter.addData(lists);
    }
}


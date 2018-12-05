package com.zimu.my2018.quyouui.core.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.R;
import com.zimu.my2018.quyouui.R2;
import com.zimu.my2018.quyouui.core.adapter.ImagePagerAdapter;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;
import com.zimu.my2018.quyouui.utils.UrlUtils;
import com.zimu.my2018.quyouui.widget.custom.buttomlayout.BottomLayout;
import com.zimu.my2018.quyouui.widget.data.photo.NetPhotoData;
import com.zimu.my2018.quyouui.widget.viewpager.HackyViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 图片查看界面
 */
public class PhotoDetailActivity extends BaseTitleActivity {

    public static final String EXTRA_IMAGE_LIST_URLS = "image_list_urls";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    private static final String STATE_POSITION = "STATE_POSITION";

    @BindView(R2.id.viewpager)
    HackyViewPager mPager;
    @BindView(R2.id.bottom_layout)
    BottomLayout bottom_layout;

    private ImagePagerAdapter mMAdapter;

    private ArrayList<String> mUrls;
    private List<NetPhotoData> urls2;
    private int pagerPosition;
    private boolean isEdit, isLocal;

    public static Intent getDiyIntent(Context context, List<NetPhotoData> netPhotoDataList,
                                      int position, boolean isEdit, boolean isLocal) {
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_IMAGE_LIST_URLS, (Serializable) netPhotoDataList);
        intent.putExtras(bundle);
        if (isEdit) {
            intent.putExtra("isEdit", isEdit);
        }
        intent.putExtra(EXTRA_IMAGE_INDEX, position);
        intent.putExtra("isLocal", isLocal);
        return intent;
    }

    @Override
    protected void initParam() {
        super.initParam();
        Intent intent = getIntent();
        urls2 = (List<NetPhotoData>) intent.getSerializableExtra(EXTRA_IMAGE_LIST_URLS);
        mUrls = new ArrayList<>();
        if (urls2 != null && urls2.size() > 0) {
            for (int i = 0; i < urls2.size(); i++) {
                NetPhotoData data = urls2.get(i);
                String netPath = data.getNetPath();
                if (StringUtils.checkNullPoint(netPath)) {
                    mUrls.add(UrlUtils.getLxUrl720(netPath));
                } else {
                    String localPath = data.getLocalPath();
                    mUrls.add(UrlUtils.getLxUrl720(localPath));
                }
            }
        }
        pagerPosition = intent.getIntExtra(EXTRA_IMAGE_INDEX, 0);
        isEdit = intent.getBooleanExtra("isEdit", false);
        isLocal = intent.getBooleanExtra("isLocal", false);
    }

    @Override
    protected void initBeforeAddView() {
        super.initBeforeAddView();
        if (!isEdit) {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        /*   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION*/
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);
            }
        }

    }

    @Override
    protected boolean bindTitle() {
        return isEdit;
    }

    @Override
    protected int getAppView() {
        return R.layout.activity_photo_detail_view;
    }

    @Override
    protected void initView() {
        setTitle();
        setRefreshLayoutInVisble();
        bottom_layout.setContent(urls2.get(pagerPosition).getPicDes());
        bottom_layout.setPage(pagerPosition + 1 + "/" + mUrls.size());

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerPosition = position;
                bottom_layout.setContent(urls2.get(position).getPicDes());
                bottom_layout.setPage(position + 1 + "/" + mUrls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mMAdapter = new ImagePagerAdapter(getSupportFragmentManager(), mUrls, isLocal);
        mPager.setAdapter(mMAdapter);
        mPager.setCurrentItem(pagerPosition);
    }

    @Override
    protected void initListener() {
        super.initListener();
        setOnBackListener(() -> {
            if (isEdit) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("result", (Serializable) urls2);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
            }
            finish();
        });
    }

    @Override
    protected void loadData() {
        showContent(true);
    }

    private void setTitle() {
        if (isEdit) {
            setTitle("图片预览", R.mipmap.icon_delete, v -> onPicDelClick());
        } else {
            setTitle("图片预览");
        }
    }

    /**
     * 删除操作进行时
     */
    private void onPicDelClick() {
        mUrls.remove(pagerPosition);
        urls2.remove(pagerPosition);
        if (urls2.size() <= 0) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("result", (Serializable) urls2);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
        mMAdapter = new ImagePagerAdapter(getSupportFragmentManager(), mUrls, isLocal);
        mPager.setAdapter(mMAdapter);
        if (pagerPosition >= mUrls.size()) {
            pagerPosition = mUrls.size() - 1;
        }
        mPager.setCurrentItem(pagerPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }
        mPager.setCurrentItem(pagerPosition);
    }
}


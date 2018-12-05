package com.zimu.my2018.appcase.scenic.scenicfreehandmap;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.constant.ScenicType;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author hxl
 * @Package com.haichou.xlqy.appcase.scenic.scenicfreehandmap
 * @Description: freehand map
 * @date 2018/10/09
 */
public class ScenicFreehandMapActivity extends BaseTitleActivity implements ScenicFreehandMapContract.View{

    @BindView(R2.id.ll_map)
    LinearLayout ll_map;
    @BindView(R2.id.pb_map)
    ProgressBar pb_map;

   @Inject
   ScenicFreehandMapPresenter mPresenter;

    private WebView wv_map;

    private String mMapUrl;
    private String mScenicName;

   public static Intent getDiyIntent(Context context, String mapUrl, String scenicName) {
       Intent intent = new Intent(context, ScenicFreehandMapActivity.class);
       intent.putExtra("mapUrl", mapUrl);
       intent.putExtra("scenicName", scenicName);
       return intent;
   }

    @Override
    protected void initParam() {
        super.initParam();
        Intent intent = getIntent();
        mMapUrl = intent.getStringExtra("mapUrl");
        mScenicName = intent.getStringExtra("scenicName");
    }

    @Override
   protected void initAppPresenter() {
      super.initAppPresenter();
      mPresenter.attachView(this);
   }

   @Override
   protected void destroyAppPresenter() {
      super.destroyAppPresenter();
      mPresenter.detachView();
   }

   @Override
   protected int getAppView() {
      return R.layout.activity_freehand_map_view;
   }

   @Override
   protected void initView() {
      setTitle(mScenicName);
      setRefreshLayoutInVisble();
      intiWebView();
   }

    private void intiWebView() {
        wv_map =  new WebView(getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) ;
        wv_map.setLayoutParams(layoutParams);
        ll_map.addView(wv_map, 1);
        WebSettings settings = wv_map.getSettings();

        settings.setDomStorageEnabled(true); // Dom Storage存储机制
        settings.setDatabaseEnabled(true); // SQL Database 存储机制
        String dbPath = getApplicationContext().getDir("db",Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dbPath);
        settings.setAppCacheEnabled(true);// Application Cache 存储机制
        String cachePath = getApplicationContext().getDir("cache",Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(cachePath);
        settings.setAppCacheMaxSize(5*1024*1024);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存
        settings.setJavaScriptEnabled(true);

    }

    @Override
   protected void loadData() {
        showContent(true);
        showMapData();
   }

    private void showMapData() {
        wv_map.loadUrl(ScenicType.baseUrl + mMapUrl);
        wv_map.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wv_map.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 100) {
                    pb_map.setVisibility(View.GONE);
                } else {
                    if (pb_map.getVisibility() == View.GONE) {
                        pb_map.setVisibility(View.GONE);
                    } else {
                        pb_map.setVisibility(View.VISIBLE);
                    }
                    pb_map.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wv_map != null) {
            ViewParent parent = wv_map.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(wv_map);
            }
            wv_map.removeAllViews();
            wv_map.destroy();
            wv_map = null;
        }
    }
}
package com.zimu.my2018.quyouui.core.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zimu.my2018.quyoulib.utils.DeviceUtils;
import com.zimu.my2018.quyouui.R;
import com.zimu.my2018.quyouui.R2;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;
import com.zimu.my2018.quyouui.third.photo.CutType;
import com.zimu.my2018.quyouui.utils.BitmapUtil;
import com.zimu.my2018.quyouui.widget.cutimg.CutSelectImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能：
 * 描述：剪切图片页面
 * Created by hxl on 2018/10/25
 */
public class CutPicViewActivity extends BaseTitleActivity {

    private static final String TAG = "CutPicViewActivity";

    @BindView(R2.id.cropImageView)
    CutSelectImageView cropImageView;
    @BindView(R2.id.buttonRotateImage)
    TextView button_Rotate;
    @BindView(R2.id.button1_1)
    TextView button1_1;
    @BindView(R2.id.buttonCircle)
    TextView button_Circle;

    private String path = null;
    private String mLocalSaveImgPath;
    private int showType;

    public static Intent getDiyIntent(Context context, String localPath, String savePath, int showType) {
        Intent intent = new Intent(context, CutPicViewActivity.class);
        intent.putExtra("localPath", localPath);
        intent.putExtra("savePath", savePath);
        intent.putExtra("showType", showType);
        return intent;
    }

    @Override
    protected void initParam() {
        super.initParam();
        path = getIntent().getStringExtra("localPath");
        mLocalSaveImgPath = getIntent().getStringExtra("savePath");
        showType = getIntent().getIntExtra("showType", 0);
    }

    @Override
    protected int getAppView() {
        return R.layout.activity_cut_pic_view;
    }

    @Override
    protected void initView() {
        setRefreshLayoutInVisble();
        initTitle();
        File dst = new File(path);
        // 根据屏幕大小获取最佳bitmap
        int width = DeviceUtils.deviceWidth(this);
        int height = DeviceUtils.deviceWidth(this);
        Bitmap bitmap = BitmapUtil.comPressdImage(dst, width * 2, height * 2);
        cropImageView.setImageBitmap(bitmap);
    }

    @Override
    protected void loadData() {
        switch (showType) {
            case CutType.CUT_1_1_TYPE:
                cropImageView.setCropMode(CutSelectImageView.CropMode.RATIO_1_1);
                button1_1.setVisibility(View.GONE);
                button_Circle.setVisibility(View.GONE);
                break;
            case CutType.CUT_4_3_TYPE:
                cropImageView.setCropMode(CutSelectImageView.CropMode.RATIO_4_3);
                button1_1.setVisibility(View.GONE);
                button_Circle.setVisibility(View.GONE);
                break;
            case CutType.CUT_16_9_TYPE:
                cropImageView.setCropMode(CutSelectImageView.CropMode.RATIO_16_9);
                button1_1.setVisibility(View.GONE);
                button_Circle.setVisibility(View.GONE);
                break;
            case CutType.CUT_CIRCLE_TYPE:
                button1_1.setVisibility(View.GONE);
                button_Circle.setVisibility(View.GONE);
                break;
            default:
                button1_1.setVisibility(View.VISIBLE);
                button_Circle.setVisibility(View.VISIBLE);
                break;
        }
        showContent(true);
    }

    private void initTitle() {
        setTitle("图片剪切", "保存", view -> {
            view.setClickable(false);
            if (cutAndSaveImage()) {
                view.setClickable(true);
                return;
            }
            // 关闭当前Activity
            finish();
        });
    }

    /**
     * 剪切保存图片
     *
     * @return
     */
    private boolean cutAndSaveImage() {
        Bitmap bitmap = cropImageView.getCroppedBitmap();
        if (null != bitmap) {
            Log.e(TAG, "截取图片大小： width = " + bitmap.getWidth() + "; height = " + bitmap.getHeight());
        } else {
            return true;
        }

        // 保存截取的图片
        if (null != mLocalSaveImgPath && bitmap != null) {
            BitmapUtil.saveBitmap(bitmap, mLocalSaveImgPath);
        }

        // 释放bitmap对象
        if (null != bitmap && !bitmap.isRecycled()) {
            bitmap.recycle();
        }

        // 返回剪切路径
        Intent mSavePathIntent = new Intent();
        mSavePathIntent.putExtra("savePath", mLocalSaveImgPath);
        setResult(RESULT_OK, mSavePathIntent);
        return false;
    }


    @OnClick({R2.id.button1_1, R2.id.buttonCircle, R2.id.buttonRotateImage})
    public void onTouchClick(View view) {
        resetTextStatus();
        view.setSelected(true);
        int viewId = view.getId();
        if (viewId == R.id.button1_1) {
            cropImageView.setCropMode(CutSelectImageView.CropMode.RATIO_1_1);
        } else if (viewId == R.id.buttonCircle) {
            cropImageView.setCropMode(CutSelectImageView.CropMode.CIRCLE);
        } else if (viewId == R.id.buttonRotateImage) {
            cropImageView.rotateImage(CutSelectImageView.RotateDegrees.ROTATE_90D);
        }
    }

    private void resetTextStatus() {
        button_Rotate.setSelected(false);
        button1_1.setSelected(false);
        button_Circle.setSelected(false);
    }
}


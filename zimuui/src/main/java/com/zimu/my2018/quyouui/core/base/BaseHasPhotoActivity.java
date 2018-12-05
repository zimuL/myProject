package com.zimu.my2018.quyouui.core.base;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.activity.CutPicViewActivity;
import com.zimu.my2018.quyouui.third.photo.CutType;
import com.zimu.my2018.quyouui.third.photo.PhotoType;
import com.zimu.my2018.quyouui.utils.BitmapUtil;
import com.zimu.my2018.quyouui.widget.data.photo.NetPhotoData;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public abstract class BaseHasPhotoActivity extends BaseMvpActivity {

    public final static int PHOTO_PICK_SINGLE_REQUEST = 0x9001;
    public final static int PHOTO_CUT_REQUEST = 0x9002;
    public final static int PHOTO_PICK_MULT_REQUEST = 0x9003;

    private int mSingChooseType;
    private PicGetCallBack picGetCallBack;
    private MultPicGetCallBack mMultPicGetCallBack;

    /**
     * 单选
     *
     * @param type           PhotoType.SING_PHOTO 和 PhotoType.SING_CUT_PHOTO
     * @param picGetCallBack
     */
    public void gotoSingChoosePic(int type, PicGetCallBack picGetCallBack) {
        this.mSingChooseType = type;
        this.picGetCallBack = picGetCallBack;
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(hasPermission -> {
                    if (hasPermission) {
                        PhotoPicker.builder()
                                .setPhotoCount(1)
                                .setShowCamera(true)
                                .setShowGif(true)
                                .setPreviewEnabled(false)
                                .start(this, PHOTO_PICK_SINGLE_REQUEST);
                    } else {
                        showOpenPermissionDialog(this);
                    }
                });
    }


    /**
     * 多选图片
     *
     * @param count
     * @param multPicGetCallBack
     */
    public void gotoMultChoosePic(int count, MultPicGetCallBack multPicGetCallBack) {
        this.mMultPicGetCallBack = multPicGetCallBack;
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(hasPermission -> {
                    if (hasPermission) {
                        PhotoPicker.builder()
                                .setPhotoCount(count)
                                .setShowCamera(true)
                                .setShowGif(true)
                                .setPreviewEnabled(false)
                                .start(this, PHOTO_PICK_MULT_REQUEST);
                    } else {
                        showOpenPermissionDialog(this);
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICK_SINGLE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (photos != null && photos.size() > 0) {
                    if (picGetCallBack != null) {
                        if (mSingChooseType == PhotoType.SING_PHOTO) {
                            picGetCallBack.onPicGetPath(photos.get(0));
                        } else if (mSingChooseType == PhotoType.SING_CUT_PHOTO) {
                            gotoCutPicActivity(photos.get(0));
                        }
                    }
                } else {
                    showToast("没有选择图片");
                }
            }
        } else if (requestCode == PHOTO_CUT_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                String savePath = data.getStringExtra("savePath");
                if (StringUtils.checkNullPoint(savePath) && picGetCallBack != null) {
                    picGetCallBack.onPicGetPath(savePath);
                }
            }
        } else if (requestCode == PHOTO_PICK_MULT_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (photos != null && photos.size() > 0) {
                    List<NetPhotoData> netPhotoDataList = new ArrayList<>();
                    for (String pic : photos) {
                        NetPhotoData netPhotoData = new NetPhotoData();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(pic, options);
                        int height = options.outHeight;
                        int width = options.outWidth;
                        netPhotoData.setLocalPath(pic);
                        netPhotoData.setHeight(height);
                        netPhotoData.setWidth(width);
                        netPhotoDataList.add(netPhotoData);
                    }

                    if (mMultPicGetCallBack != null) {
                        mMultPicGetCallBack.onPicGetPath(netPhotoDataList);
                    }
                } else {
                    showToast("没有选择图片");
                }
            }
        }
    }

    /**
     * 跳转剪切图片界面
     *
     * @param localpath
     */
    private void gotoCutPicActivity(String localpath) {
        String imagePath = Environment.getExternalStorageDirectory().getPath() + File.separator
                + BitmapUtil.getPhotoFileName();
        int cutCustomType = getType();
        startActivityForResult(CutPicViewActivity.getDiyIntent(this, localpath, imagePath, cutCustomType), PHOTO_CUT_REQUEST);
    }

    protected int getType() {
        return CutType.CUT_CUSTOM_TYPE;
    }

    /**
     * 跳转剪切图片界面
     *
     * @param localpath
     * @param type
     */
    private void gotoCutPicActivity(String localpath, int type) {
        String imagePath = Environment.getExternalStorageDirectory().getPath() + File.separator
                + BitmapUtil.getPhotoFileName();
        startActivityForResult(CutPicViewActivity.getDiyIntent(this, localpath, imagePath, type), PHOTO_CUT_REQUEST);
    }

    public interface PicGetCallBack {
        void onPicGetPath(String local);
    }

    public interface MultPicGetCallBack {
        void onPicGetPath(List<NetPhotoData> netPhotoDataList);
    }
}


package com.zimu.my2018.quyoulib.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/8
 */
public class FileUtils {

    private static File fileDir;

    /*图片压缩 根据图片path路径获得bitmap*/
    public static Bitmap decodeFile(String fPath) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inDither = false; // Disable Dithering mode
        opts.inPurgeable = true; // Tell to gc that whether it needs free
        opts.inInputShareable = true; // Which kind of reference will be used to
        BitmapFactory.decodeFile(fPath, opts);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        if (opts.outHeight > REQUIRED_SIZE || opts.outWidth > REQUIRED_SIZE) {
            final int heightRatio = Math.round((float) opts.outHeight
                    / (float) REQUIRED_SIZE);
            final int widthRatio = Math.round((float) opts.outWidth
                    / (float) REQUIRED_SIZE);
            scale = heightRatio < widthRatio ? heightRatio : widthRatio;//
        }
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = scale;
        Bitmap bm = BitmapFactory.decodeFile(fPath, opts).copy(Bitmap.Config.ARGB_8888, false);
        return bm;
    }
    /*网络图片转化为bitmap*/
    public static Bitmap getBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("bitmap000", "getBitmap: "+bitmap);
        return bitmap;
    }
    /*创建目录*/
    public static void setMkdir(String path)
    {
        File file = new File(path);
        if(!file.exists())
        {
            file.mkdirs();
            Log.e("file", "目录不存在  创建目录");
        }else{
            Log.e("file", "目录存在");
        }
    }

    /*获取目录名称*/
    public static String getFileName(String url)
    {
        int lastIndexStart = url.lastIndexOf("/");
        if(lastIndexStart!=-1)
        {
            return url.substring(lastIndexStart+1, url.length());
        }else{
            return null;
        }
    }
    /*删除该目录下的文件*/
    public static void delFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /********************上传图片*************************/
    private static String APP_DIR_NAME = "uploading_photos";
    private static String FILE_DIR_NAME = "files";
    private static String mRootDir;
    private static String mAppRootDir;
    private static String mFileDir;
    public static void init() {
        mRootDir = getRootPath();
        if (mRootDir != null && !"".equals(mRootDir)) {
            mAppRootDir = mRootDir + "/" + APP_DIR_NAME;
            mFileDir = mAppRootDir + "/" + FILE_DIR_NAME;
            File appDir = new File(mAppRootDir);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            fileDir = new File(mAppRootDir + "/" + FILE_DIR_NAME);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

        } else {
            mRootDir = "";
            mAppRootDir = "";
            mFileDir = "";
        }
    }


    public static String getFileDir(){
        return mFileDir;
    }




    public static String getRootPath() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getAbsolutePath(); // filePath:  /sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/data"; // filePath:  /data/data/
        }
    }

    /**
     * 打开文件
     * 兼容7.0
     * @param context     activity
     * @param file        File
     * @param contentType 文件类型如：文本（text/html）
     *                    当手机中没有一个app可以打开file时会抛ActivityNotFoundException
     */
    public static void startActionFile(Context context, File file, String contentType) throws ActivityNotFoundException {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//增加读写权限
        intent.setDataAndType(getUriForFile(context, file), contentType);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 打开相机
     * 兼容7.0
     *
     * @param activity    Activity
     * @param file        File
     * @param requestCode result requestCode
     */
    public static void startActionCapture(Activity activity, File file, int requestCode) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(activity, file));
        activity.startActivityForResult(intent, requestCode);
    }


    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.example.administrator.mojingquyou.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

}


package com.zimu.my2018.quyouui.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public class BitmapUtil {
    /**
     * 获取适合屏幕尺寸的bitmap对象
     *
     * @param dst
     * @param width
     * @param height
     * @return
     */
    public static Bitmap comPressdImage(File dst, int width, int height) {
        Bitmap bitmap = null;
        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = null;
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), opts);
                // 计算图片缩放比例
                final int minSideLength = Math.min(width, height);
                opts.inSampleSize = computeSampleSize(opts, minSideLength,
                        width * height);
                opts.inJustDecodeBounds = false;
                opts.inInputShareable = true;
                opts.inPurgeable = true;
            }
            try {

                bitmap = BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * android 原生最有图片大小计算
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    /**
     * 最有尺寸
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeInitialSampleSize(BitmapFactory.Options options,
                                               int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }


    /**
     * 保存方法
     */
    public static void saveBitmap(Bitmap bm, String path) {

        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 保存bitmap
     *
     * @param bm
     * @param fileName
     * @param savePath
     * @return
     */
    public static String saveBitmap(Bitmap bm, String fileName, String savePath) {
        if (null == bm)
            return null;

        File floder = new File(savePath);
        if (!floder.exists())
            floder.mkdirs();

        String result;
        BufferedOutputStream bos;

        File myCaptureFile = new File(floder, fileName);

        try {
            if (!myCaptureFile.exists())
                myCaptureFile.createNewFile();

            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);

            result = myCaptureFile.getAbsolutePath();

            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }


    /**
     * 获取截取图片名称
     *
     * @return
     */
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.getDefault());
        return dateFormat.format(date) + ".jpg";
    }


    /*
     * 更新MEDIA里面的状态，可以在图库中看见下载的图片
     */
    public static void galleryAddPic(Context context, String path) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }


    /**
     * 获取超过屏幕长度的view的bitmap
     * 不适用listview scrollveiw
     *
     * @param view
     * @return
     */
    public static Bitmap getHeightMoreScreenView(View view) {

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);


        return bitmap;
    }


    public static void notifyPicAdd2Pic(Context context, String fileAbsolutePath, String fileName) {
        try {
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), fileAbsolutePath, fileName, null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileAbsolutePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取没有绘制的XML视图文件的bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap getNotShowViewBitmap(int width, int height, View view) {
        //启用绘图缓存
        view.setDrawingCacheEnabled(true);
        //调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        //这个方法也非常重要，设置布局的尺寸和位置
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        //获得绘图缓存中的Bitmap
        view.buildDrawingCache();
        return view.getDrawingCache();
    }


    /**
     * 将两个bitmap 结合起来
     *
     * @param topBitmap
     * @param bottomBitmap
     * @return
     */
    public static Bitmap mergeVerBitmap(Bitmap topBitmap, Bitmap bottomBitmap) {
        int width = topBitmap.getWidth() > bottomBitmap.getWidth() ? topBitmap.getWidth() : bottomBitmap.getWidth();
        int height = topBitmap.getHeight() + bottomBitmap.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);


        // 将bmp1绘制在画布上
        Rect srcRect = new Rect(0, 0, topBitmap.getWidth(), topBitmap.getHeight());// 截取bmp1中的矩形区域
        Rect dstRect = new Rect(0, 0, topBitmap.getWidth(), topBitmap.getHeight());//// bmp1在目标画布中的位置
        canvas.drawBitmap(topBitmap, srcRect, dstRect, null);

        // 将bmp2绘制在画布上
        srcRect = new Rect(0, 0, bottomBitmap.getWidth(), bottomBitmap.getHeight());// 截取bmp1中的矩形区域
        dstRect = new Rect(0, topBitmap.getHeight(), bottomBitmap.getWidth(), height);// bmp2在目标画布中的位置
        canvas.drawBitmap(bottomBitmap, srcRect, dstRect, null);

        return bitmap;
    }


    public static Bitmap compressInSameSize(String filePath, int insampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = insampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap WeChatBitmapToByteArray(Bitmap bmp) {

        // 首先进行一次大范围的压缩

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
        float zoom = (float) Math.sqrt(32 * 1024 / (float) output.toByteArray().length); //获取缩放比例

        // 设置矩阵数据
        Matrix matrix = new Matrix();
        matrix.setScale(zoom, zoom);

        // 根据矩阵数据进行新bitmap的创建
        Bitmap resultBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);

        output.reset();

        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);

        // 如果进行了上面的压缩后，依旧大于32K，就进行小范围的微调压缩
        while (output.toByteArray().length > 32 * 1024) {
            matrix.setScale(0.9f, 0.9f);//每次缩小 1/10

            resultBitmap = Bitmap.createBitmap(
                    resultBitmap, 0, 0,
                    resultBitmap.getWidth(), resultBitmap.getHeight(), matrix, true);

            output.reset();
        }

        return resultBitmap;
    }
}

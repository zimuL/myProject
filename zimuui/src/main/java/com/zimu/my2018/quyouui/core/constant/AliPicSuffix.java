package com.zimu.my2018.quyouui.core.constant;

/**
 * aliyun图片操作符规则
 * <p>
 * https://help.aliyun.com/document_detail/44694.html?spm=a2c4g.11186623.6.978.qkCX8j
 * <p>
 * <p>
 * 操作名称：resize
 * <p>
 * 指定宽高缩放
 * 名称	描述	取值范围
 * m	指定缩略的模式：
 * - lfit：等比缩放，限制在设定在指定w与h的矩形内的最大图片。
 * - mfit：等比缩放，延伸出指定w与h的矩形框外的最小图片。
 * - fill：固定宽高，将延伸出指定w与h的矩形框外的最小图片进行居中裁剪。
 * - pad：固定宽高，缩略填充。
 * - fixed：固定宽高，强制缩略	[lfit,mfit,fill,pad,fixed]，默认为lfit。
 * w	指定目标缩略图的宽度。	1-4096
 * h	指定目标缩略图的高度。	1-4096
 * l	指定目标缩略图的最长边。	1-4096
 * s	指定目标缩略图的最短边。	1-4096
 * limit	指定当目标缩略图大于原图时是否处理。值是 1 表示不处理；值是 0 表示处理。	0/1, 默认是 1
 * color	当缩放模式选择为pad（缩略填充）时，可以选择填充的颜色(默认是白色)参数的填写方式：采用16进制颜色码表示，如00FF00（绿色）。	[000000-FFFFFF]
 * 按比例缩放
 * 名称	描述	取值范围
 * p	倍数百分比。 小于100，即是缩小，大于100即是放大。	1-1000
 * <p>
 * <p>
 * <p>
 * 操作名称：format
 * <p>
 * 名称	描述
 * jpg	将原图保存成jpg格式，如果原图是png、webp、bmp存在透明通道，默认会把透明填充成白色。
 * png	将原图保存成png格式。
 * webp	将原图保存成webp格式。
 * bmp	将原图保存成bmp格式。
 * gif	将gif格式保存成gif格式，非gif格式是按原图格式保存。
 * tiff	将原图保存成tiff格式。
 * <p>
 * <p>
 * <p>
 * 操作名称：circle
 * <p>
 * 参数	描述	取值
 * r	从图片取出的圆形区域的半径	半径 r 不能超过原图的最小边的一半。如果超过，则圆的大小仍然是原圆的最大内切圆。
 * <p>
 * <p>
 * 操作名称：auto-orient
 * <p>
 * 参数	描述	取值范围
 * value	进行自动旋转
 * 0：表示按原图默认方向，不进行自动旋转。
 * 1：先进行图片进行旋转，然后再进行缩略
 * Created by hxl on 2018/10/23
 */
public class AliPicSuffix {

    public static String pci_constant = "?x-oss-process=image";


    /**
     * 单边缩略-按长边短边
     *
     * @param width
     * @return
     */
    public static String getLTypePicSuffix(int width) {
        String str = pci_constant + "/resize,l_" + width + getWebpPicSuffix() + getAutoOrient();
        return str;
    }

    /**
     * 单边缩略-按宽
     *
     * @param width
     * @return
     */
    public static String getWTypePicSuffix(int width) {
        String str = pci_constant + "/resize,w_" + width + getWebpPicSuffix() + getAutoOrient();
        return str;
    }


    /**
     * 单边缩略-强制宽高
     *
     * @param width
     * @return
     */
    public static String getFixedTypePicSuffix(int width, int height) {
        String str = pci_constant + "/resize,m_fixed,w_" + width + ",h_" + height + getWebpPicSuffix() + getAutoOrient();
        return str;
    }


    /**
     * 获取web格式图片
     *
     * @return
     */
    public static String getWebpPicSuffix() {
        return "/format,webp";
    }

    /**
     * 取内切圆形图
     *
     * @param radius
     * @return
     */
    public static String getCirclePicSuffix(int radius) {
        return "/circle,r_" + radius;
    }


    public static String getAutoOrient() {
        return "/auto-orient,1";
    }
}


package com.zimu.my2018.quyoulib.utils;

import java.util.Locale;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/22
 */
public class NumberUtil {

    private NumberUtil() {

    }

    public static String getTenThousandData(int number) {
        if (number >= 10000) {
            double div = ArithmeticUtil.div(number, 10000);
            return String.format(Locale.getDefault(), "%s万", ArithmeticUtil.round(div, 2));
        } else {
            return String.valueOf(number);
        }
    }

    public static String getKm(double d) {
        if (d >= 1000) {
            double div = ArithmeticUtil.div(d, 1000);
            return String.format(Locale.getDefault(), "%s千米" , ArithmeticUtil.round(div, 2));
        } else {
            return String.format(Locale.getDefault(), "%d米" , d);
        }
    }
}

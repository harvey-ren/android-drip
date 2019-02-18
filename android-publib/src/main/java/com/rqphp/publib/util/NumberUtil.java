package com.rqphp.publib.util;

import java.text.DecimalFormat;

public class NumberUtil {

    /**
     * 时间显示格式化
     *
     * @param i
     * @return
     */
    public static String numberFormat(int i) {
        return i > 9 ? i + "" : "0" + i;
    }

    /**
     * 保留两位小数
     *
     * @param num
     * @return
     */
    public static double getTwoDecimal(double num) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return Double.valueOf(decimalFormat.format(num));
        } catch (Throwable throwable) {
            LogUtil.error(throwable.toString());
        }
        return 0.00;
    }


    /**
     * 保留一位小数
     *
     * @param num
     * @return
     */
    public static double getOneDecimal(double num) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return Double.valueOf(decimalFormat.format(num));
        } catch (Throwable throwable) {
            LogUtil.error(throwable.toString());
        }
        return 0.0;
    }

}

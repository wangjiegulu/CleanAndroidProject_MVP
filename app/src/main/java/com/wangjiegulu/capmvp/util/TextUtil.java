package com.wangjiegulu.capmvp.util;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class TextUtil {

    public static String convertReadableCount(int count) {
        if (count < 1000) {
            return String.valueOf(count);
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(1);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);

        return numberFormat.format(1.0 * count / 1000).replace(",", "") + "k";
    }

}

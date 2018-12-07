package net.chinaedu.aedu.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 数字相关方法
 */
public class AeduDigitalUtils {

    public static byte[] int2byte(int intValue) {
        byte[] value = new byte[4];
        for (int i = 0; i < value.length; i++) {
            value[i] = (byte) ((intValue >>> (24 - (8 * i))) & 0xff);

        }

        return value;
    }

    public static int byte2int(byte[] fourChar) {
        int value = (int) (fourChar[0] << 24 & 0xff000000)
                | (int) (fourChar[1] << 16 & 0x00ff0000)
                | (int) (fourChar[2] << 8 & 0x0000ff00)
                | (int) (fourChar[3] & 0x000000ff);

        return value;
    }

    public static long byte2long(byte[] bb) {
        return ((((long) bb[7] & 0xff) << 56)
                | (((long) bb[6] & 0xff) << 48)
                | (((long) bb[5] & 0xff) << 40)
                | (((long) bb[4] & 0xff) << 32)
                | (((long) bb[3] & 0xff) << 24)
                | (((long) bb[2] & 0xff) << 16)
                | (((long) bb[1] & 0xff) << 8)
                | (((long) bb[0] & 0xff) << 0));
    }

    public static void long2byte(byte[] bb) {
        long l = Double.doubleToLongBits(0);
        for (int i = 0; i < 4; i++) {
            bb[0 + i] = new Long(l).byteValue();
            l = l >> 8;
        }
    }

    public static double round(double d, int n) {
        d = d * Math.pow(10, n);
        d += 0.5d;
        d = (long) d;
        d = d / Math.pow(10d, n);

        return d;
    }

    /**
     * 保留几位小数
     * @param number
     * @param pattern "###,##0.00"  写#的是有值就写值，没值就不写;写0的是有值就写值，没值就写0。
     * @param scale
     * @param mode 设置取舍模式 RoundingMode.HALF_UP  RoundingMode.HALF_DOWN
     * @return
     */
    public static String getRoundStr(double number, String pattern , int scale, RoundingMode mode) {
        DecimalFormat f1 = new DecimalFormat(pattern);
        f1.setRoundingMode(mode);
        f1.setMaximumFractionDigits(scale);
        f1.setMinimumFractionDigits(scale);
        return f1.format(number);
    }

    /**
     * 保留几位小数
     */
    public static String getRoundStr(double number, int scale) {
        return getRoundStr(number,"###,##0.00",scale, RoundingMode.HALF_UP);
    }

    public static String getRomdomVerifyCode() {
        String str = new String();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str = str + random.nextInt(9);
        }
        return str;
    }
}

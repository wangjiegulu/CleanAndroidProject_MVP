package com.wangjiegulu.capmvp.usagesupport.usage.compat;


import java.util.Arrays;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 03/08/2017.
 */
public final class ArraysCompat {
    private ArraysCompat() {
    }

    static boolean deepEquals0(Object e1, Object e2) {
        Class<?> cl1 = e1.getClass().getComponentType();
        Class<?> cl2 = e2.getClass().getComponentType();

        if (cl1 != cl2) {
            return false;
        }
        if (e1 instanceof Object[])
            return Arrays.deepEquals((Object[]) e1, (Object[]) e2);
        else if (cl1 == byte.class)
            return Arrays.equals((byte[]) e1, (byte[]) e2);
        else if (cl1 == short.class)
            return Arrays.equals((short[]) e1, (short[]) e2);
        else if (cl1 == int.class)
            return Arrays.equals((int[]) e1, (int[]) e2);
        else if (cl1 == long.class)
            return Arrays.equals((long[]) e1, (long[]) e2);
        else if (cl1 == char.class)
            return Arrays.equals((char[]) e1, (char[]) e2);
        else if (cl1 == float.class)
            return Arrays.equals((float[]) e1, (float[]) e2);
        else if (cl1 == double.class)
            return Arrays.equals((double[]) e1, (double[]) e2);
        else if (cl1 == boolean.class)
            return Arrays.equals((boolean[]) e1, (boolean[]) e2);
        else
            return e1.equals(e2);
    }
}

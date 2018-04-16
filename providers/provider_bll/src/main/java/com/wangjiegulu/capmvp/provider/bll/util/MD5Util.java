package com.wangjiegulu.capmvp.provider.bll.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/5/16.
 */
public final class MD5Util {
    private MD5Util() {
    }

    private static final String TAG = MD5Util.class.getSimpleName();

    public static String md5(String str) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append('0');
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();  //md5 32bit
            // result = buf.toString().substring(8, 24))); //md5 16bit
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "md5() error", e);
        }
        return result;
    }

    /**
     * get md5 of the file
     */
    public static String getFileMd5(File file) {
        MessageDigest messageDigest;
        RandomAccessFile randomAccessFile = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            if (file == null) {
                return "";
            }
            if (!file.exists()) {
                return "";
            }
            randomAccessFile=new RandomAccessFile(file,"r");
            byte[] bytes=new byte[1024*1024*10];
            int len=0;
            while ((len=randomAccessFile.read(bytes))!=-1){
                messageDigest.update(bytes,0, len);
            }
            BigInteger bigInt = new BigInteger(1, messageDigest.digest());
            String md5 = bigInt.toString(16);
            while (md5.length() < 32) {
                md5 = "0" + md5;
            }
            return md5;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}

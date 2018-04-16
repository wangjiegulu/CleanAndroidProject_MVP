package com.wangjiegulu.capmvp.provider.dal.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

//import com.wangjiegulu.capmvp.provider.bll.application.ProviderApplication;

import com.wangjiegulu.capmvp.provider.dal.application.DalApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 4/10/17.
 */
public class FileAccessorImpl implements FileAccessor {
    private static final String TAG = FileAccessorImpl.class.getSimpleName();

    private String appRootPath;
    private String userDir = null;

    public FileAccessorImpl(String userDirPath) {
        this.userDir = userDirPath;
    }

    @Override
    public File getDir(FileStructure fileStructure) {
        checkInitial();
        File file = new File(
                (
                        fileStructure.isUserScope
                                ?
                                getUserRootDir(DalApplication.getInstance().getApplication())
                                :
                                getAppRootDir(DalApplication.getInstance().getApplication())
                )
                        + fileStructure.dir
        );
        boolean result = file.mkdirs();
        if (!result) {
            Log.e(TAG, "File mkdirs error: " + file.getAbsolutePath());
        }
        Log.d(TAG, "[FileStructure]getDir: " + file.getAbsolutePath());
        return file;
    }

    @Override
    public File getFile(FileStructure fileStructure, String fileName) {
        checkInitial();
        File file = new File(getDir(fileStructure), fileName);
        Log.d(TAG, "[FileStructure]getFile: " + file.getAbsolutePath());
        return file;
    }

    /**
     * 用户目录地址
     */
    public String getUserRootDir(Context context) {
        checkInitial();
        return getAppRootDir(context) + userDir + File.separator;
    }

    /**
     * app目录地址
     */
    private String getAppRootDir(Context context) {
        if (null == appRootPath) {
            appRootPath = getSDCardPath(context) + FileStructure.ROOT.dir;
        }
        return appRootPath;
    }

    /**
     * SD card目录
     */
    @Override
    public String getSDCardPath(Context context) {
        // check whether the device has external storage
        if (/*haveSDCard()*/Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            String sdcardPath = Environment.getExternalStorageDirectory().toString()
                    + File.separator;
            Log.d(TAG, "have sdcard! sdcard path: " + sdcardPath);
            return sdcardPath;
        } else {
            String dirPath = context.getCacheDir().getAbsoluteFile() + File.separator;
            Log.d(TAG, "have no sdcard! dir path: " + dirPath);
            return dirPath;
        }
    }

    private void checkInitial() {
        if (null == userDir) {
            throw new IllegalArgumentException("FileStructure has not initialized!");
        }
    }

    /**
     * 删除目录
     *
     * @param dir         目录
     * @param isDeleteDir 是否删除目录
     * @return true:删除成功, false:删除失败
     */
    @Override
    public boolean deleteDir(File dir, boolean isDeleteDir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return false;
        }
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if (null == files) {
            return false;
        }
        for (File file : files) {
            if (file.isFile()) {
                if (!deleteFile(file)) {
                    return false;
                }
            } else if (file.isDirectory()) {
                if (!deleteDir(file, true)) {
                    return false;
                }
            }
        }
        return !isDeleteDir || dir.delete();
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return true:删除成功, false:删除失败
     */
    @Override
    public boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }


    /**
     * oldPath: 图片缓存的路径
     * newPath: 图片缓存copy的路径
     */
    @Override
    public boolean copyFile(String oldPath, String newPath) {
        try {
            int byteRead;
            File newFile = new File(newPath);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }

            File oldFile = new File(oldPath);
            if (oldFile.exists() && newFile.exists()) {
                //清空文件内容
                FileWriter fileWriter = new FileWriter(newFile);
                fileWriter.write("");
                fileWriter.flush();
                fileWriter.close();

                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("复制文件操作出错");
            e.printStackTrace();
        }
        return false;
    }
}

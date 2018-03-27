package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.file;

import android.content.Context;

import java.io.File;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 4/10/17.
 */
public interface FileAccessor {
    File getDir(FileStructure fileStructure);

    File getFile(FileStructure fileStructure, String fileName);

    String getSDCardPath(Context context);

    boolean deleteDir(File dir, boolean isDeleteDir);

    boolean deleteFile(File file);

    boolean copyFile(String oldPath, String newPath);
}

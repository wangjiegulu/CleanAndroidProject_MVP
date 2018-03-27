package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.file;

import java.io.File;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 5/16/15.
 */
enum FileStructure {
    ROOT("wangjiegulu/CleanAndroidProject_MVP" + File.separator, false),
    // ********************** app dir structure **********************
    APP_CONFIG("config" + File.separator, false),
    // ...
    // ********************** user dir structure **********************
    USER_DATA("data" + File.separator, true),
    USER_CONFIG("config" + File.separator, true),
    USER_CACHE("cache" + File.separator, true),
    USER_CACHE_PHOTO(USER_CACHE.dir + "photo" + File.separator, true),
    USER_CACHE_DOWNLOAD(USER_CACHE.dir + "download", true);

    public String dir;
    public boolean isUserScope;

    FileStructure(String dir, boolean isUserScope) {
        this.dir = dir;
        this.isUserScope = isUserScope;
    }

}

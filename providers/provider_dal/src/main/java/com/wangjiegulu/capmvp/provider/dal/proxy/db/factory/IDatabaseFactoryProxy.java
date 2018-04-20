package com.wangjiegulu.capmvp.provider.dal.proxy.db.factory;

import android.support.annotation.NonNull;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 20/04/2018.
 */
public interface IDatabaseFactoryProxy {

    boolean resetDatabase(@NonNull String databaseName);

    boolean resetDatabaseIfCrash();

}

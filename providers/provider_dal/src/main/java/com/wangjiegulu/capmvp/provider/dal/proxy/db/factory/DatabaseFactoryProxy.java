package com.wangjiegulu.capmvp.provider.dal.proxy.db.factory;

import android.support.annotation.NonNull;

import com.wangjiegulu.capmvp.provider.dal.db.helper.DatabaseFactory;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 20/04/2018.
 */
public class DatabaseFactoryProxy implements IDatabaseFactoryProxy {
    private static IDatabaseFactoryProxy instance = new DatabaseFactoryProxy();

    public static IDatabaseFactoryProxy getInstance(){
        return instance;
    }

    private DatabaseFactory databaseFactory;
    private DatabaseFactoryProxy() {
        databaseFactory = new DatabaseFactory();
    }


    @Override
    public boolean resetDatabase(@NonNull String databaseName) {
        return databaseFactory.resetDatabase(databaseName);
    }

    @Override
    public boolean resetDatabaseIfCrash() {
        return databaseFactory.resetDatabaseIfCrash();
    }
}

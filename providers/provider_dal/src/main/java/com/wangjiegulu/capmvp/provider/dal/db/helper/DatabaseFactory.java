package com.wangjiegulu.capmvp.provider.dal.db.helper;

import android.support.annotation.NonNull;

import com.wangjie.rapidorm.core.config.TableConfig;
import com.wangjie.rapidorm.core.connection.DatabaseProcessor;
import com.wangjie.rapidorm.core.connection.RapidORMConnection;
import com.wangjie.rapidorm.core.delegate.openhelper.RapidORMDefaultSQLiteOpenHelperDelegate;
import com.wangjiegulu.capmvp.provider.dal.application.DalApplication;

import java.util.HashMap;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 6/25/15.
 */
public class DatabaseFactory extends RapidORMConnection<RapidORMDefaultSQLiteOpenHelperDelegate> {
    private static class Holder {
        private static DatabaseFactory instance = new DatabaseFactory();
    }

    public synchronized static DatabaseFactory getInstance() {
        return Holder.instance;
    }

    private DatabaseFactory() {
        super();
    }

    private String databaseName;

    @Override
    public boolean resetDatabase(@NonNull String databaseName) {
//        this.databaseName = databaseName;
//        return super.resetDatabase(databaseName);
//        if (databaseName.equals(this.databaseName)) {
//            return false;
//        } else {
            this.databaseName = databaseName;
            DatabaseProcessor.getInstance().resetRapidORMDatabaseOpenHelper(this.getRapidORMDatabaseOpenHelper(databaseName));
            DatabaseProcessor.getInstance().getDb();
            return true;
//        }
    }

    @Override
    public boolean resetDatabaseIfCrash() {
        resetDatabase(databaseName);
        return true;
    }

    @Override
    protected RapidORMDefaultSQLiteOpenHelperDelegate getRapidORMDatabaseOpenHelper(@NonNull String databaseName) {
        return new RapidORMDefaultSQLiteOpenHelperDelegate(new MyDatabaseOpenHelper(DalApplication.getInstance().getApplication(), databaseName));
    }

    @Override
    protected void registerTableConfigMapper(HashMap<Class, TableConfig> tableConfigMapper) {
//        tableConfigMapper.put(User.class, new User_RORM());
//        tableConfigMapper.put(UserPreference.class, new UserPreference_RORM());
        // register all table config here...
    }

}

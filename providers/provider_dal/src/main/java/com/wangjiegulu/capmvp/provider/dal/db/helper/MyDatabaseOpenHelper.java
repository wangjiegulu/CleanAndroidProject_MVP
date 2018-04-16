package com.wangjiegulu.capmvp.provider.dal.db.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.wangjie.rapidorm.core.connection.DatabaseProcessor;
import com.wangjie.rapidorm.core.delegate.database.RapidORMDefaultSQLiteDatabaseDelegate;
import com.wangjie.rapidorm.core.delegate.database.RapidORMSQLiteDatabaseDelegate;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 6/25/15.
 */
public class MyDatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION_V1_0_0 = 1;
    private static final int VERSION_CURRENT = VERSION_V1_0_0;

    public MyDatabaseOpenHelper(Context context, String name) {
        this(context, name, VERSION_CURRENT);
    }

    public MyDatabaseOpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public MyDatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MyDatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    private RapidORMSQLiteDatabaseDelegate dbDelegate;

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbDelegate = new RapidORMDefaultSQLiteDatabaseDelegate(db);
        DatabaseProcessor databaseProcessor = DatabaseProcessor.getInstance();
        databaseProcessor.initializeDatabase(dbDelegate);

        DatabaseProcessor.getInstance().createAllTable(dbDelegate, true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dbDelegate = new RapidORMDefaultSQLiteDatabaseDelegate(db);
        DatabaseProcessor databaseProcessor = DatabaseProcessor.getInstance();
        databaseProcessor.initializeDatabase(dbDelegate);

        // 把drop掉的表都构建起来
        onCreate(db);
    }
}

package com.wangjiegulu.capmvp.provider.dal.proxy.db.dao;

import com.wangjie.rapidorm.core.dao.BaseDaoImpl;
import com.wangjie.rapidorm.core.delegate.database.RapidORMSQLiteDatabaseDelegate;
import com.wangjie.rapidorm.core.generate.builder.DeleteBuilder;
import com.wangjie.rapidorm.core.generate.builder.QueryBuilder;
import com.wangjie.rapidorm.core.generate.builder.UpdateBuilder;
import com.wangjie.rapidorm.util.func.RapidOrmFunc1;

import java.util.Collection;
import java.util.List;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 20/04/2018.
 */
public class DaoProxy<T> implements IDaoProxy<T> {
    private BaseDaoImpl<T> baseDao;

    public DaoProxy(Class<T> clazz) {
        baseDao = new BaseDaoImpl<>(clazz);
    }

    @Override
    public void insert(T model) throws Exception {
        baseDao.insert(model);
    }

    @Override
    public void update(T model) throws Exception {
        baseDao.update(model);
    }

    @Override
    public void delete(T model) throws Exception {
        baseDao.delete(model);
    }

    @Override
    public void deleteAll() throws Exception {
        baseDao.deleteAll();
    }

    @Override
    public List<T> queryAll() throws Exception {
        return baseDao.queryAll();
    }

    @Override
    public List<T> rawQuery(String sql, String[] selectionArgs) throws Exception {
        return baseDao.rawQuery(sql, selectionArgs);
    }

    @Override
    public void rawExecute(String sql, Object[] bindArgs) throws Exception {
        baseDao.rawExecute(sql, bindArgs);
    }

    @Override
    public void insertInTx(T[] models) throws Exception {
        baseDao.insertInTx(models);
    }

    @Override
    public void insertInTx(Iterable<T> models) throws Exception {
        baseDao.insertInTx(models);
    }

    @Override
    public void updateInTx(T[] models) throws Exception {
        baseDao.updateInTx(models);
    }

    @Override
    public void updateInTx(Iterable<T> models) throws Exception {
        baseDao.updateInTx(models);
    }

    @Override
    public void deleteInTx(T[] models) throws Exception {
        baseDao.deleteInTx(models);
    }

    @Override
    public void deleteInTx(Iterable<T> models) throws Exception {
        baseDao.deleteInTx(models);
    }

    @Override
    public void executeInTx(RapidORMSQLiteDatabaseDelegate db, RapidOrmFunc1 func1) throws Exception {
        baseDao.executeInTx(db, func1);
    }

    @Override
    public void executeInTx(RapidOrmFunc1 func1) throws Exception {
        baseDao.executeInTx(func1);
    }

    @Override
    public void executeInTxSync(RapidOrmFunc1 func1) throws Exception {
        baseDao.executeInTxSync(func1);
    }

    @Override
    public boolean isExist(T model) throws Exception {
        return baseDao.isExist(model);
    }

    @Override
    public void insertOrUpdate(T model) throws Exception {
        baseDao.insertOrUpdate(model);
    }

    @Override
    public void insertOrUpdate(T[] models) throws Exception {
        baseDao.insertOrUpdate(models);
    }

    @Override
    public void insertOrUpdate(Collection<T> models) throws Exception {
        baseDao.insertOrUpdate(models);
    }

    @Override
    public void insertOrUpdateInTx(T[] models) throws Exception {
        baseDao.insertOrUpdate(models);
    }

    @Override
    public void insertOrUpdateInTx(Collection<T> models) throws Exception {
        baseDao.insertOrUpdateInTx(models);
    }

    @Override
    public QueryBuilder<T> queryBuilder() {
        return baseDao.queryBuilder();
    }

    @Override
    public UpdateBuilder<T> updateBuilder() {
        return baseDao.updateBuilder();
    }

    @Override
    public DeleteBuilder<T> deleteBuilder() {
        return baseDao.deleteBuilder();
    }
}

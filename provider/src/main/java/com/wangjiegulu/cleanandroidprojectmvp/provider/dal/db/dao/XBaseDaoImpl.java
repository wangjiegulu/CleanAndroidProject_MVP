package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.dao;

import com.wangjie.rapidorm.core.dao.BaseDaoImpl;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/4/16.
 */
public class XBaseDaoImpl<T> extends BaseDaoImpl<T> implements XBaseDao<T> {
    private static final String TAG = XBaseDaoImpl.class.getSimpleName();

    public XBaseDaoImpl(Class<T> clazz) {
        super(clazz);
    }
}

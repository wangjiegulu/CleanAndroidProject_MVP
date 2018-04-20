package com.wangjiegulu.capmvp.provider.dal.db.dao;

import com.wangjiegulu.capmvp.provider.dal.proxy.db.dao.DaoProxy;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/4/16.
 */
public class XBaseDaoImpl<T> extends DaoProxy<T> implements XBaseDao<T> {
    private static final String TAG = XBaseDaoImpl.class.getSimpleName();

    public XBaseDaoImpl(Class<T> clazz) {
        super(clazz);
    }
}

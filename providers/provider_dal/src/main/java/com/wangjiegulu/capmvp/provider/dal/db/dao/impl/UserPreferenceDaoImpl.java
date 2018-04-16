package com.wangjiegulu.capmvp.provider.dal.db.dao.impl;

import com.wangjie.rapidorm.core.generate.builder.Where;
import com.wangjiegulu.capmvp.provider.dal.db.dao.XBaseDaoImpl;
import com.wangjiegulu.capmvp.provider.dal.db.dao.contract.UserPreferenceDao;
import com.wangjiegulu.capmvp.provider.dal.db.po.UserPreference;
import com.wangjiegulu.capmvp.provider.dal.db.po.UserPreference_RORM;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 17/08/2017.
 */
public class UserPreferenceDaoImpl extends XBaseDaoImpl<UserPreference> implements UserPreferenceDao {
    public UserPreferenceDaoImpl() {
        super(UserPreference.class);
    }

    @Override
    public void insertOrUpdate(String key, String value) throws Exception {
        UserPreference userPreference = new UserPreference();
        userPreference.setKey(key);
        userPreference.setValue(value);
        insertOrUpdate(userPreference);
    }

    @Override
    public UserPreference queryByKey(String key) throws Exception {
        return queryBuilder()
                .setWhere(Where.eq(UserPreference_RORM.KEY, key))
                .queryFirst();
    }
}

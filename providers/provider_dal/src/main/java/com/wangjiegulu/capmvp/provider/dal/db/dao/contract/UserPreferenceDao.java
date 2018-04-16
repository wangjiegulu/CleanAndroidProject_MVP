package com.wangjiegulu.capmvp.provider.dal.db.dao.contract;

import com.wangjiegulu.capmvp.provider.dal.db.dao.XBaseDao;
import com.wangjiegulu.capmvp.provider.dal.db.po.UserPreference;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 17/08/2017.
 */
public interface UserPreferenceDao extends XBaseDao<UserPreference> {

    void insertOrUpdate(String key, String value) throws Exception;

    UserPreference queryByKey(String key) throws Exception;
}

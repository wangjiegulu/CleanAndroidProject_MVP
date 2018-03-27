package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model;

import com.wangjie.rapidorm.api.annotations.Column;
import com.wangjie.rapidorm.api.annotations.Table;

import java.io.Serializable;

/**
 * SharePreference like
 *
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 17/08/2017.
 */
@Table
public class UserPreference implements Serializable {
    
    @Column(primaryKey = true)
    String key;

    @Column
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

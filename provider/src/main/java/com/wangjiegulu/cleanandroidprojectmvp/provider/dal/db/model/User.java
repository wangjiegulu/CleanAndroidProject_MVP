package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model;

import com.wangjie.rapidorm.api.annotations.Column;
import com.wangjie.rapidorm.api.annotations.Table;

import java.io.Serializable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/3/16.
 */
@Table
public class User implements Serializable {

    /* 未登录用户user id */
    public static final long USER_NOT_LOGIN_USER_ID = -0x338933;
    /* 未登录用户 */
    public static final User USER_NOT_LOGIN = new User(USER_NOT_LOGIN_USER_ID);

    @Column(primaryKey = true, name = "user_id")
    Long userId;
    /**
     * 用户token
     */
    @Column
    String token;
    @Column(name = "nick_name")
    String nickName;
    @Column(name = "avatar_url")
    String avatarUrl;

    public User() {
    }

    private User(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public long getUserId(long defaultValue) {
        return null == userId ? defaultValue : userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getUserIdDefaultNotLogin() {
        return getUserId(USER_NOT_LOGIN_USER_ID);
    }

    public boolean isNotLogin() {
        return User.USER_NOT_LOGIN == this;
    }
}

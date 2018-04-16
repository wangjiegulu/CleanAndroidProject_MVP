package com.wangjiegulu.capmvp.provider.bll.interactor.contract;

import com.wangjiegulu.capmvp.provider.dal.db.po.User;

import io.reactivex.Observable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 10/31/16.
 */
public interface UserInteractor {

    /**
     * 获取当前用户信息
     */
    Observable<User> getCurrentUserInfo();

//    /**
//     * 同步保存用户信息
//     *
//     * @param user
//     */
//    void saveLoginInfoSync(User user);
}

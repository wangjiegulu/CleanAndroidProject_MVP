package com.wangjiegulu.capmvp.provider.bll.interactor.bo;

import com.wangjiegulu.capmvp.provider.dal.db.po.User;
import com.wangjiegulu.capmvp.provider.dal.http.pojo.GithubRepository;
import com.wangjiegulu.rapidooo.api.OOO;
import com.wangjiegulu.rapidooo.api.OOOs;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 16/04/2018.
 */
@OOOs(suffix = "BO", ooos = {
        @OOO(from = User.class),
        @OOO(from = GithubRepository.class)
})
public class BOGenerator {
    public static final String DO_PACKAGE = "com.wangjiegulu.capmvp.provider.dal.db.po";
}

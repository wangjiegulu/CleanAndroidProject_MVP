package com.wangjiegulu.capmvp.provider.bll.interactor.contract;


import com.wangjiegulu.capmvp.provider.bll.interactor.bo.GithubRepositoryBO;

import io.reactivex.Observable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public interface GithubInteractor {
    Observable<GithubRepositoryBO> requestUserGithubRepositories(String githubUsername);
}

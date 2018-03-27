package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract;

import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.entity.GithubRepository;

import io.reactivex.Observable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public interface GithubInteractor {
    Observable<GithubRepository> requestUserGithubRepositories(String githubUsername);
}

package com.wangjiegulu.capmvp.provider.bll.interactor.impl;

import com.google.gson.reflect.TypeToken;

import com.wangjiegulu.capmvp.provider.bll.application.configuration.scheduler.ProviderSchedulers;
import com.wangjiegulu.capmvp.provider.bll.interactor.base.BaseInteractor;
import com.wangjiegulu.capmvp.provider.bll.interactor.bo.GithubRepositoryBO;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GithubInteractor;
import com.wangjiegulu.capmvp.provider.dal.http.XRequestCreator;
import com.wangjiegulu.capmvp.provider.dal.http.pojo.GithubRepository;
import com.wangjiegulu.capmvp.provider.dal.http.webapi.WebApi;
import com.wangjiegulu.capmvp.usagesupport.compat.subscriber.RxCompatException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class GithubInteractorImpl extends BaseInteractor implements GithubInteractor {

    @Inject
    XRequestCreator xRequestCreator;

    public GithubInteractorImpl() {
        getProviderUserInteractorComponent().inject(this);
    }

    @Override
    public Observable<GithubRepositoryBO> requestUserGithubRepositories(String githubUsername) {
        return xRequestCreator.createRequest(WebApi.User.REPOS.replace("{user}", githubUsername))
                .get()
                .<List<GithubRepository>>observable(TypeToken.getParameterized(List.class, GithubRepository.class).getType())
                .subscribeOn(ProviderSchedulers.net())
                .doOnNext(o -> {
                    if (null == o) {
                        throw new RxCompatException();
                    }
                })
                .flatMap(Observable::fromIterable)
                .map(GithubRepositoryBO::create);
    }


}

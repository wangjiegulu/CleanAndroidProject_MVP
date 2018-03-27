package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.impl;

import com.google.gson.reflect.TypeToken;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.configuration.scheduler.ProviderSchedulers;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.base.BaseInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.GithubInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.XRequestCreator;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.entity.GithubRepository;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.webapi.WebApi;
import com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.compat.subscriber.RxCompatException;

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
    public Observable<GithubRepository> requestUserGithubRepositories(String githubUsername) {
        return xRequestCreator.createRequest(WebApi.User.REPOS.replace("{user}", githubUsername))
                .get()
                .<List<GithubRepository>>observable(TypeToken.getParameterized(List.class, GithubRepository.class).getType())
                .subscribeOn(ProviderSchedulers.net())
                .doOnNext(o -> {
                    if (null == o) {
                        throw new RxCompatException();
                    }
                })
                .flatMap(Observable::fromIterable);
    }


}

package com.wangjiegulu.capmvp.ui.main;

import com.wangjiegulu.capmvp.R;
import com.wangjiegulu.capmvp.application.configuration.scheduler.AppSchedulers;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GithubInteractor;
import com.wangjiegulu.capmvp.ui.base.BasePresenter;
import com.wangjiegulu.capmvp.usagesupport.compat.RxCompat;
import com.wangjiegulu.capmvp.usagesupport.compat.RxCompatSingleObserver;
import com.wangjiegulu.capmvp.usagesupport.compat.subscriber.RxCompatException;
import com.wangjiegulu.mvparchitecture.library.viewer.Viewer;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class MainPresenter extends BasePresenter implements MainContract.IMainPresenter {
    private WeakReference<MainContract.IMainViewer> viewer;

    @Inject
    GithubInteractor githubInteractor;

    @Inject
    public MainPresenter(Viewer viewer) {
        this.viewer = new WeakReference<>((MainContract.IMainViewer) viewer);
    }

    @Override
    public void requestUserRepositories(String githubUsername) {
        viewer.get().showLoadingDialog(R.string.loading);
        githubInteractor.requestUserGithubRepos(githubUsername)
                .map(GithubRepoMainVO::create)
                .toList()
                .observeOn(AppSchedulers.main())
                .compose(RxCompat.doOnSuccessOrError(() -> viewer.get().cancelLoadingDialog()))
                .subscribe(new RxCompatSingleObserver<List<GithubRepoMainVO>>() {
                    @Override
                    public void onSubscribeCompat(Disposable d) {
                        attachDisposable(d);
                    }

                    @Override
                    public void onErrorCompat(RxCompatException compatThrowable) {
                        super.onErrorCompat(compatThrowable);
                        viewer.get().showToast(compatThrowable.getMessage());
                    }

                    @Override
                    public void onSuccessCompat(List<GithubRepoMainVO> mainRepositoryVMs) {
                        viewer.get().onRequestUserRepositories(mainRepositoryVMs);
                    }
                });
    }
}

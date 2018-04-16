package com.wangjiegulu.capmvp.ui.main;

import com.wangjiegulu.capmvp.ui.main.adapter.RepositoryAdapter;
import com.wangjiegulu.mvparchitecture.library.presenter.Presenter;
import com.wangjiegulu.mvparchitecture.library.viewer.Viewer;

import java.util.List;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public interface MainContract {
    interface IMainViewer extends Viewer, RepositoryAdapter.OnRepositoryAdapterItemClickListener {
        void onRequestUserRepositories(List<GithubRepositoryMainVO> githubRepositories);
    }

    interface IMainPresenter extends Presenter {
        void requestUserRepositories(String githubUsername);
    }
}

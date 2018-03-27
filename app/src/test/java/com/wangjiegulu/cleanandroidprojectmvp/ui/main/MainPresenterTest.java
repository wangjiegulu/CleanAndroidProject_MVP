package com.wangjiegulu.cleanandroidprojectmvp.ui.main;

import android.os.SystemClock;

import com.wangjiegulu.cleanandroidprojectmvp.base.ArgumentsAnswer;
import com.wangjiegulu.cleanandroidprojectmvp.base.AppImmediateSchedulerRule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.GithubInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.entity.GithubRepository;
import com.wangjiegulu.cleanandroidprojectmvp.ui.main.vm.MainRepositoryVM;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class MainPresenterTest {

    @Rule
    public AppImmediateSchedulerRule appImmediateSchedulerRule = new AppImmediateSchedulerRule();

    private MainPresenter presenter;
    @Mock
    private GithubInteractor githubInteractor;
    @Mock
    private MainContract.IMainViewer viewer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = spy(new MainPresenter(viewer));
        presenter.githubInteractor = githubInteractor;

    }

    @Test
    @SuppressWarnings("unchecked")
    public void requestUserRepositories_success() throws Exception {
        doReturn(
                Observable.create(emitter -> {
                    SystemClock.sleep(1000);
                    emitter.onNext(new GithubRepository());
                    emitter.onNext(new GithubRepository());
                    emitter.onNext(new GithubRepository());
                    emitter.onComplete();
                })
        )
                .when(githubInteractor).requestUserGithubRepositories(anyString());

        ArgumentsAnswer argumentsAnswer;
        doAnswer(argumentsAnswer = new ArgumentsAnswer()).when(viewer).onRequestUserRepositories(anyListOf(MainRepositoryVM.class));

        presenter.requestUserRepositories("wangjiegulu");

        verify(presenter, times(1)).attachDisposable(anyObject());
        verify(viewer, times(1)).onRequestUserRepositories(anyListOf(MainRepositoryVM.class));
        verify(viewer, times(1)).showLoadingDialog(anyInt());

        assertNotNull(argumentsAnswer);
        assertEquals(1, argumentsAnswer.getArguments().length);
        assertEquals(3, ((List<MainRepositoryVM>) argumentsAnswer.getArgument(0)).size());

    }

    @Test
    public void requestUserRepositories_error() throws Exception {
        doReturn(Observable.error(new RuntimeException()))
                .when(githubInteractor).requestUserGithubRepositories(anyString());

        presenter.requestUserRepositories("wangjiegulu");

        verify(presenter, times(1)).attachDisposable(anyObject());
        verify(viewer, never()).onRequestUserRepositories(anyListOf(MainRepositoryVM.class));
        verify(viewer, times(1)).showToast(anyString());

    }


}

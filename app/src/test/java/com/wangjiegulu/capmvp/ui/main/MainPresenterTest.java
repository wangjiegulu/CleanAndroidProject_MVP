package com.wangjiegulu.capmvp.ui.main;

import com.wangjiegulu.capmvp.base.AppImmediateSchedulerRule;
import com.wangjiegulu.capmvp.base.ArgumentsAnswer;
import com.wangjiegulu.capmvp.provider.bll.interactor.bo.GithubRepoBO;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GithubInteractor;

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
                    emitter.onNext(new GithubRepoBO());
                    emitter.onNext(new GithubRepoBO());
                    emitter.onNext(new GithubRepoBO());
                    emitter.onComplete();
                })
        )
                .when(githubInteractor).requestUserGithubRepos(anyString());

        ArgumentsAnswer argumentsAnswer = new ArgumentsAnswer();
        doAnswer(argumentsAnswer).when(viewer).onRequestUserRepositories(anyListOf(GithubRepoMainVO.class));

        presenter.requestUserRepositories("wangjiegulu");

        verify(presenter, times(1)).attachDisposable(anyObject());
        verify(viewer, times(1)).onRequestUserRepositories(anyListOf(GithubRepoMainVO.class));
        verify(viewer, times(1)).showLoadingDialog(anyInt());

        assertNotNull(argumentsAnswer);
        assertEquals(1, argumentsAnswer.getArguments().length);
        assertEquals(3, ((List<GithubRepoMainVO>) argumentsAnswer.getArgument(0)).size());

    }

    @Test
    public void requestUserRepositories_error() throws Exception {
        doReturn(Observable.error(new RuntimeException()))
                .when(githubInteractor).requestUserGithubRepos(anyString());

        presenter.requestUserRepositories("wangjiegulu");

        verify(presenter, times(1)).attachDisposable(anyObject());
        verify(viewer, never()).onRequestUserRepositories(anyListOf(GithubRepoMainVO.class));
        verify(viewer, times(1)).showToast(anyString());

    }


}

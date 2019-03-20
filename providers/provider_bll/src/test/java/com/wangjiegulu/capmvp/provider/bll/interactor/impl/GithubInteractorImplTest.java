package com.wangjiegulu.capmvp.provider.bll.interactor.impl;

import com.wangjiegulu.capmvp.provider.bll.base.ProviderImmediateSchedulerRule;
import com.wangjiegulu.capmvp.provider.bll.interactor.bo.GithubRepoBO;
import com.wangjiegulu.capmvp.provider.dal.http.XRequestRepository;
import com.wangjiegulu.capmvp.provider.dal.http.pojo.GithubRepo;
import com.wangjiegulu.capmvp.provider.dal.proxy.net.http.IRequestProxy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class GithubInteractorImplTest {
    @Rule
    public ProviderImmediateSchedulerRule providerImmediateSchedulerRule = new ProviderImmediateSchedulerRule();

    @Mock
    GithubInteractorImpl githubInteractor;

    @Mock
    XRequestRepository xRequestRepository;

    @Mock
    IRequestProxy requestProxy;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        githubInteractor.xRequestRepository = xRequestRepository;
        doReturn(requestProxy).when(xRequestRepository).createRequest(anyString());
        doReturn(requestProxy).when(requestProxy).get();
        doReturn(requestProxy).when(requestProxy).post();
        doReturn(requestProxy).when(requestProxy).addParameter(anyString(), any());
    }

    @Test
    public void requestUserGithubRepositories_success() {
        doReturn(Observable.fromArray(
                new GithubRepo(),
                new GithubRepo(),
                new GithubRepo()
        ).toList().toObservable()).when(requestProxy).observable(any(Type.class));

        TestObserver<GithubRepoBO> testObserver = new TestObserver<>();

        doCallRealMethod().when(githubInteractor).requestUserGithubRepos(anyString());
        githubInteractor.requestUserGithubRepos("wangjiegulu")
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        assertEquals(3, testObserver.valueCount());
    }

    @Test
    public void requestUserGithubRepositories_error() {
        RuntimeException runtimeException = new RuntimeException();
        doReturn(Observable.error(runtimeException))
                .when(requestProxy).observable(any(Type.class));

        TestObserver<GithubRepoBO> testObserver = new TestObserver<>();

        doCallRealMethod().when(githubInteractor).requestUserGithubRepos(anyString());
        githubInteractor.requestUserGithubRepos("wangjiegulu")
                .subscribe(testObserver);

        testObserver.assertError(runtimeException);
        testObserver.assertNoValues();
    }


}

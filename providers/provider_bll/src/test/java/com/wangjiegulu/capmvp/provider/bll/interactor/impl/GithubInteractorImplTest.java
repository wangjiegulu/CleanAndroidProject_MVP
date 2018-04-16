package com.wangjiegulu.capmvp.provider.bll.interactor.impl;

import com.wangjiegulu.capmvp.provider.bll.base.ProviderImmediateSchedulerRule;
import com.wangjiegulu.capmvp.provider.bll.interactor.bo.GithubRepositoryBO;
import com.wangjiegulu.capmvp.provider.dal.http.XRequestCreator;
import com.wangjiegulu.capmvp.provider.dal.http.pojo.GithubRepository;
import com.wangjiegulu.dal.request.core.request.XRequest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

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
    XRequestCreator xRequestCreator;

    @Spy
    XRequest xRequest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        githubInteractor.xRequestCreator = xRequestCreator;
        doReturn(xRequest).when(xRequestCreator).createRequest(anyString());
    }

    @Test
    public void requestUserGithubRepositories_success() {
        doReturn(Observable.fromArray(
                new GithubRepository(),
                new GithubRepository(),
                new GithubRepository()
        ).toList().toObservable()).when(xRequest).observable(any(Type.class));

        TestObserver<GithubRepositoryBO> testObserver = new TestObserver<>();

        doCallRealMethod().when(githubInteractor).requestUserGithubRepositories(anyString());
        githubInteractor.requestUserGithubRepositories("wangjiegulu")
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        assertEquals(3, testObserver.valueCount());
    }

    @Test
    public void requestUserGithubRepositories_error() {
        RuntimeException runtimeException = new RuntimeException();
        doReturn(Observable.error(runtimeException))
                .when(xRequest).observable(any(Type.class));

        TestObserver<GithubRepositoryBO> testObserver = new TestObserver<>();

        doCallRealMethod().when(githubInteractor).requestUserGithubRepositories(anyString());
        githubInteractor.requestUserGithubRepositories("wangjiegulu")
                .subscribe(testObserver);

        testObserver.assertError(runtimeException);
        testObserver.assertNoValues();
    }


}

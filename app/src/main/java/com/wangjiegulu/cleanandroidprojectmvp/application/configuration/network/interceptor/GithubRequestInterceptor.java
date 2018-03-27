package com.wangjiegulu.cleanandroidprojectmvp.application.configuration.network.interceptor;

import com.wangjiegulu.dal.request.core.interceptor.IRequestInterceptor;
import com.wangjiegulu.dal.request.core.request.XRequest;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class GithubRequestInterceptor implements IRequestInterceptor {
    @Override
    public void onRequestIntercept(XRequest xRequest) throws Throwable {
        xRequest.addHeader("Accept", "application/vnd.github.v3+json")
                .addHeader("Content-Type", "application/json; charset=utf-8");


    }
}

package com.wangjiegulu.capmvp.provider.dal.http;

import com.wangjiegulu.capmvp.provider.dal.http.webapi.WebApiConstants;
import com.wangjiegulu.capmvp.provider.dal.proxy.net.http.IRequestProxy;
import com.wangjiegulu.capmvp.provider.dal.proxy.net.http.RequestProxy;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class XRequestRepository {

    /**
     * 默认https
     */
    public IRequestProxy createRequest(String httpUrl) {
        return new RequestProxy(WebApiConstants.formatHttpsWebApi(httpUrl));
//        return XRequest.create(httpUrl);
    }

//
//    public XRequest createHttpsRequest(String httpUrl) {
//        return XRequest.create(WebApiConstants.formatHttpsWebApi(httpUrl));
//    }

}
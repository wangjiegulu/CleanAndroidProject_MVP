package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http;

import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.webapi.WebApiConstants;
import com.wangjiegulu.dal.request.core.request.XRequest;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class XRequestCreator {

    /**
     * 默认https
     */
    public XRequest createRequest(String httpUrl) {
        return XRequest.create(WebApiConstants.formatHttpsWebApi(httpUrl));
//        return XRequest.create(httpUrl);
    }

//
//    public XRequest createHttpsRequest(String httpUrl) {
//        return XRequest.create(WebApiConstants.formatHttpsWebApi(httpUrl));
//    }

}
package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.response;

import com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.compat.subscriber.RxCompatException;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class BaseHttpResponse {

    public boolean isBizSucceed(boolean defaultValue) {
        // TODO: 23/03/2018 wangjie
        return false;
    }

    public RxCompatException toCompatException() {
//        return new RxCompatException(getCode(RxCompatException.CODE_DEFAULT), message);
        return new RxCompatException();
    }
}

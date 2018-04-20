package com.wangjiegulu.capmvp.provider.dal.proxy.net.http;

import java.lang.reflect.Type;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 20/04/2018.
 */
public interface IResponseConverterProxy {
    <T> T convert(IRequestProxy iRequestProxy, byte[] responseBytes, Type responseType);
}

package com.wangjiegulu.capmvp.provider.dal.proxy.net.http;

import android.support.annotation.Nullable;

import java.io.File;
import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 17/04/2018.
 */
public interface IRequestProxy {

    void setMethod(String method);

    IRequestProxy get();

    IRequestProxy post();

    IRequestProxy setRetryCount(int retryCount);

    IRequestProxy addHeader(String name, Object value);

    IRequestProxy addParameter(String name, @Nullable Object value);

    IRequestProxy addFileParameter(String name, String filename, String mediaType, File file);

    IRequestProxy addFileParameter(String name, String filename, String mediaType, byte[] fileBytes);

    IRequestProxy setTimeoutSeconds(long timeoutSeconds);

    IRequestProxy addConfiguration(String key, Object configuration);

    <T> Observable<T> observable(Type responseType);

    <T> Observable<T> observable(Class<T> responseClass);

    IRequestProxy setResponseConverter(IResponseConverterProxy responseConverterProxy);


}

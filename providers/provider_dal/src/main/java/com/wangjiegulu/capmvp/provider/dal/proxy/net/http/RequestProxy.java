package com.wangjiegulu.capmvp.provider.dal.proxy.net.http;

import android.support.annotation.Nullable;

import com.wangjiegulu.dal.request.core.converter.ResponseConverter;
import com.wangjiegulu.dal.request.core.request.XRequest;

import java.io.File;
import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 20/04/2018.
 */
public class RequestProxy implements IRequestProxy {
    XRequest xRequest;

    public RequestProxy(String url) {
        xRequest = XRequest.create(url);
    }

    @Override
    public void setMethod(String method) {
        xRequest.setMethod(method);
    }

    @Override
    public IRequestProxy get() {
        xRequest.get();
        return this;
    }

    @Override
    public IRequestProxy post() {
        xRequest.post();
        return this;
    }

    @Override
    public IRequestProxy setRetryCount(int retryCount) {
        xRequest.setRetryCount(retryCount);
        return this;
    }

    @Override
    public IRequestProxy addHeader(String name, Object value) {
        xRequest.addHeader(name, value);
        return this;
    }

    @Override
    public IRequestProxy addParameter(String name, @Nullable Object value) {
        xRequest.addParameter(name, value);
        return this;
    }

    @Override
    public IRequestProxy addFileParameter(String name, String filename, String mediaType, File file) {
        xRequest.addFileParameter(name, filename, mediaType, file);
        return this;
    }

    @Override
    public IRequestProxy addFileParameter(String name, String filename, String mediaType, byte[] fileBytes) {
        xRequest.addFileParameter(name, filename, mediaType, fileBytes);
        return this;
    }

    @Override
    public IRequestProxy setTimeoutSeconds(long timeoutSeconds) {
        xRequest.setTimeoutSeconds(timeoutSeconds);
        return this;
    }

    @Override
    public IRequestProxy addConfiguration(String key, Object configuration) {
        xRequest.addConfiguration(key, configuration);
        return this;
    }

    @Override
    public <T> Observable<T> observable(Type responseType) {
        return xRequest.observable(responseType);
    }

    @Override
    public <T> Observable<T> observable(Class<T> responseClass) {
        return xRequest.observable(responseClass);
    }

    @Override
    public IRequestProxy setResponseConverter(IResponseConverterProxy responseConverterProxy) {
        xRequest.setResponseConverter(new ResponseConverter() {
            @Override
            public <T> T convert(XRequest xRequest, byte[] responseBytes, Type responseType) {
                return responseConverterProxy.convert(RequestProxy.this, responseBytes, responseType);
            }
        });
        return this;
    }
}

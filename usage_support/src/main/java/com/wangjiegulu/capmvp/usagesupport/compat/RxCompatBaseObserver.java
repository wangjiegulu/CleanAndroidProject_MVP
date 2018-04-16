package com.wangjiegulu.capmvp.usagesupport.compat;

import android.util.Log;

import com.wangjiegulu.capmvp.usagesupport.compat.subscriber.RxCompatException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
public abstract class RxCompatBaseObserver<T> {
    public static final String RXCOMPAT_OBSERVER_TAG = RxCompatBaseObserver.class.getSimpleName();

    public final void onSubscribe(@NonNull Disposable d) {
        try {
            onSubscribeCompat(d);
        } catch (Throwable throwable) {
            Log.e(RXCOMPAT_OBSERVER_TAG, "onSubscribe() error", throwable);
        }
    }

    public final void onError(Throwable e) {
        RxCompatException rxCompatException;
        if (e instanceof RxCompatException) {
            rxCompatException = (RxCompatException) e;
        } else {
            if (isNetworkError(e)) {
                rxCompatException = new RxCompatException(RxCompatException.CODE_NETWORK, RxCompatException.ERROR_NETWORK, e);
            } else {
                // default error
                rxCompatException = new RxCompatException(e);
            }
        }
        try {
            onErrorCompat(rxCompatException);
        } catch (Throwable throwable) {
            Log.e(RXCOMPAT_OBSERVER_TAG, "onError.onErrorCompat", throwable);
        }

        Log.e(RXCOMPAT_OBSERVER_TAG, rxCompatException.getMessage()); // 单元测试失败依据，勿删除！！

        Log.e(RXCOMPAT_OBSERVER_TAG, "onError", rxCompatException.getCause());
    }

    public abstract void onSubscribeCompat(Disposable d);

    public abstract void onErrorCompat(RxCompatException compatThrowable);

    private static boolean isNetworkError(Throwable throwable) {
        return (throwable instanceof UnknownHostException) || (throwable instanceof TimeoutException)
                || (throwable instanceof SocketException) || (throwable instanceof SocketTimeoutException);
    }

}

package com.wangjiegulu.capmvp.usagesupport.compat;

import android.util.Log;

import com.wangjiegulu.capmvp.usagesupport.compat.subscriber.RxCompatException;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
public abstract class RxCompatSingleObserver<T> extends RxCompatBaseObserver implements SingleObserver<T> {

    @Override
    public final void onSuccess(@NonNull T t) {
        try {
            onSuccessCompat(t);
        } catch (Throwable throwable) {
            Log.e(RXCOMPAT_OBSERVER_TAG, "onSuccess", throwable);
        }
    }

    public abstract void onSubscribeCompat(Disposable d);

    public void onErrorCompat(RxCompatException compatThrowable) {
        // ignore
    }

    public abstract void onSuccessCompat(T t);

}

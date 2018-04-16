package com.wangjiegulu.capmvp.usagesupport.compat;

import android.util.Log;

import com.wangjiegulu.capmvp.usagesupport.compat.subscriber.RxCompatException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
public abstract class RxCompatObserver<T> extends RxCompatBaseObserver implements Observer<T> {
    public static final String RXCOMPAT_OBSERVER_TAG = RxCompatObserver.class.getSimpleName();

    @Override
    public final void onNext(T t) {
        try {
            onNextCompat(t);
        } catch (Throwable throwable) {
            Log.e(RXCOMPAT_OBSERVER_TAG, "onNext", throwable);
        }
    }

    @Override
    public final void onComplete() {
        try {
            onCompleteCompat();
        } catch (Throwable throwable) {
            Log.e(RXCOMPAT_OBSERVER_TAG, "onComplete", throwable);
        }
    }

    public abstract void onSubscribeCompat(Disposable d);

    public void onErrorCompat(RxCompatException compatThrowable) {
        // ignore
    }

    public abstract void onNextCompat(T t);

    public void onCompleteCompat() {
        // ignore
    }

}

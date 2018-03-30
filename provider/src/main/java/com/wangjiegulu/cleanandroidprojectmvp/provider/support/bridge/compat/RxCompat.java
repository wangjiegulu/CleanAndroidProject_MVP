package com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.compat;

import android.support.annotation.Nullable;

import com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.usage.XFunc0;
import com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.usage.compat.optional.OptionalCompat;

import java.lang.ref.WeakReference;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 4/15/16.
 */
public final class RxCompat {
    private RxCompat() {
    }

    public static <T> ObservableTransformer<T, T> doOnCompletedOrError(final XFunc0 doOnCompletedOrErrorFunc) {
        return upstream -> upstream
                .doOnComplete(doOnCompletedOrErrorFunc::call)
                .doOnError(throwable -> doOnCompletedOrErrorFunc.call());
    }

    public static <T> ObservableTransformer<T, T> doOnNextOrError(final XFunc0 doOnNextOrErrorFunc) {
        return upstream -> upstream
                .doOnNext(t -> doOnNextOrErrorFunc.call())
                .doOnError(throwable -> doOnNextOrErrorFunc.call());
    }

    public static <T> SingleTransformer<T, T> doOnSuccessOrError(final XFunc0 doOnNextOrErrorFunc) {
        return upstream -> upstream
                .doOnSuccess(t -> doOnNextOrErrorFunc.call())
                .doOnError(throwable -> doOnNextOrErrorFunc.call());
    }

    public static <T> ObservableTransformer<T, T> filterWeakRef(@Nullable final WeakReference weakReference) {
        return upstream -> upstream.filter(t -> null != weakReference && null != weakReference.get());
    }

    public static <T> FlowableTransformer<OptionalCompat<T>, T> deoptionalize() {
        return src ->
                src.filter(OptionalCompat::isPresent)
                .map(OptionalCompat::get);
    }

}

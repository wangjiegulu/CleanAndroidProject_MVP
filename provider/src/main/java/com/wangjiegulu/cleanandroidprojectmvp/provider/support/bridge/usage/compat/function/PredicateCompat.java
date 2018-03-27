package com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.usage.compat.function;

/**
 * java.util.function.Predicate
 *
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 17/08/2017.
 */
public interface PredicateCompat<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);
}

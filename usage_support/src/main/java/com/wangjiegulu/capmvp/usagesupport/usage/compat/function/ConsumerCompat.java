package com.wangjiegulu.capmvp.usagesupport.usage.compat.function;

/**
 * java.util.function.Consumer
 *
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 17/08/2017.
 */
public interface ConsumerCompat<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);
}

package com.wangjiegulu.capmvp.usagesupport.usage.compat.function;

/**
 * java.util.function.Function
 *
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 17/08/2017.
 */
public interface FunctionCompat<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}

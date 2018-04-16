package com.wangjiegulu.capmvp.base;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class ArgumentsAnswer<T> implements Answer<T> {
    private Object[] arguments;

    @Override
    public final T answer(InvocationOnMock invocation) throws Throwable {
        arguments = invocation.getArguments();
        return answerInternal(invocation);
    }

    public T answerInternal(InvocationOnMock invocation) throws Throwable {
        return (T) invocation.callRealMethod();
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object getArgument(int i) {
        return arguments[i];
    }

}

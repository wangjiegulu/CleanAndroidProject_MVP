package com.wangjiegulu.capmvp.usagesupport.compat.subscriber;

//import android.support.annotation.VisibleForTesting;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 10/31/16.
 */
public class RxCompatException extends RuntimeException {
    public static final int CODE_FOR_TEST = 0x998899;
    public static final int CODE_DEFAULT = 0x998833;
    public static final int CODE_NETWORK = 0x998832;
    // TODO: 10/31/16 wangjie optim
    public static final String ERROR_DEFAULT = "Something is wrong >_<!!";
    public static final String ERROR_NETWORK = "Net work error >_<!!";
    private int code;

    public RxCompatException() {
        this(CODE_DEFAULT, ERROR_DEFAULT);
    }

    public RxCompatException(Throwable cause) {
        this(CODE_DEFAULT, ERROR_DEFAULT, cause);
    }

    public RxCompatException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

//    @VisibleForTesting
    public RxCompatException(String message) {
        this(CODE_FOR_TEST, message);
    }
    public RxCompatException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

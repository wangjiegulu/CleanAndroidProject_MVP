package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.vm;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * 基本的VM
 * <p>
 * Created by wangjie on 16/1/8.
 */
public class VM<T> implements Serializable {
    public static final int TYPE_DEFAULT = -0x34544;

    private T model;

    public VM(@NonNull T model) {
        this.model = model;
    }

    @NonNull
    public T getModel() {
        return model;
    }

    public void setModel(@NonNull T model) {
        this.model = model;
    }

    public int getViewType() {
        return 0;
    }
}

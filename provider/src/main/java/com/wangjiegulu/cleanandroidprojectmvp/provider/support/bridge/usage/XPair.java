package com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.usage;

import java.io.Serializable;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 12/15/16.
 */
public class XPair<K, V> implements Serializable {
    public K key;
    public V value;

    public XPair() {

    }

    public XPair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

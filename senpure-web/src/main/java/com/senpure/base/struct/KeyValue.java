package com.senpure.base.struct;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/23.
 */
public class KeyValue<K,V>  implements Serializable{

    private static final long serialVersionUID = 2710755315144432920L;
    private  K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KeyValue{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

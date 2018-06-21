package org.hibernate.cfg;

import org.hibernate.annotations.common.reflection.java.JavaXMember;

import javax.persistence.Version;
import java.util.LinkedHashMap;

/**
 * Created by 罗中正 on 2017/9/30.
 */
public class VersionLastMap<K, V> extends LinkedHashMap<K, V> {

    private K versionKey;
    private V versionValue;

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    @Override
    public V put(K key, V value) {

        System.out.println(key.getClass()+","+value.getClass());
        V v = super.put(key, value);
        JavaXMember javaXMember = (JavaXMember) value;
        Version version = javaXMember.getAnnotation(Version.class);
        System.out.println(key+","+value+", version"+version);
        if (version != null) {
            versionKey = key;
            versionValue = value;
            return v;
        }
        if (containsKey(versionKey)) {
            remove(versionKey);
            super.put(versionKey, versionValue);
        }
        return v;
    }


}

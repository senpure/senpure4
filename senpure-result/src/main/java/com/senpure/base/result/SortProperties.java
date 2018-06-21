package com.senpure.base.result;

import java.util.*;


public class SortProperties extends Properties {

    /**
     *
     */
    private static final long serialVersionUID = -1291274099733093658L;
    private  final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();


    @Override
    public Enumeration<Object> keys() {

        return Collections.<Object> enumeration(keys);

    }


    @Override
    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);

    }


    @Override
    public Set<Object> keySet() {

        return keys;

    }


    @Override
    public Set<String> stringPropertyNames() {

        Set<String> set = new LinkedHashSet<String>();


        for (Object key : this.keys) {

            set.add((String) key);

        }


        return set;

    }
}

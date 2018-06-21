package org.hibernate.cfg;

import org.hibernate.annotations.common.reflection.java.JavaXMember;

import javax.persistence.Version;
import java.util.*;

/**
 * Created by 罗中正 on 2017/9/30.
 */
public class VersionSecondMap<K, V> extends LinkedHashMap<K, V> {

    @Override
    public V put(K key, V value) {

        JavaXMember javaXMember = (JavaXMember) value;

        //System.out.println("key ,"+key+" ,value, "+value);
        Version version = javaXMember.getAnnotation(Version.class);
        if (version != null) {
            if (size() < 1) {

                return super.put(key, value);
            }
            Iterator<Map.Entry<K, V>> iterator = entrySet().iterator();
            List<Map.Entry<K, V>> entries = new ArrayList<>();
            while (iterator.hasNext()) {
                entries.add(iterator.next());
            }
            clear();
            V v = null;
            for (int i = 0; i < entries.size(); i++) {
                Map.Entry<K, V> entry = entries.get(i);
                super.put(entry.getKey(), entry.getValue());
                if (i == 0) {
                    v = super.put(key, value);
                }
            }
            return v;
        }
        V v = super.put(key, value);
        return v;
    }


}

package org.apache.ignite.tests.load;

/**
 * Created by twer on 11/11/2016.
 */
public class KeyValuePair {
    private final String cacheName;
    private final  Object key;
    private final  Object value;

    public KeyValuePair(String cacheName, Object key, Object value) {
        this.cacheName = cacheName;
        this.key = key;
        this.value = value;
    }

    public String getCacheName() {
        return cacheName;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}

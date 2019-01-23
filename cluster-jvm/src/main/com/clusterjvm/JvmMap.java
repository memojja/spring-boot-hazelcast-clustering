package com.clusterjvm;

import com.clusterapi.ClustredMap;
import com.clusterapi.DestroyableObject;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class JvmMap implements ClustredMap {

    private final DestroyableObject destroyableObject;
    private ConcurrentHashMap concurrentHashMap;

    public JvmMap(DestroyableObject destroyableObject) {
        this.destroyableObject = destroyableObject;
        concurrentHashMap = new ConcurrentHashMap();
    }

    @Override
    public Optional<String> get(Object key) {
        return Optional.ofNullable(concurrentHashMap.get(key));
    }

    @Override
    public void put(Object key, Object value) {
        concurrentHashMap.put(key,value);
    }

    @Override
    public void remove(Object key) {
        concurrentHashMap.remove(key);
    }

    @Override
    public Map copyOf() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

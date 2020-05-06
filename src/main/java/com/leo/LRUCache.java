package com.leo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
// 继承LinkedHashMap
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int MAX_CACHE_SIZE = Short.MAX_VALUE;

    public LRUCache(int cacheSize) {
        // accessOrder要设置为true，按访问排序
        super(cacheSize, 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        // 超过阈值时返回true，进行LRU淘汰
        return size() > MAX_CACHE_SIZE;
    }

}


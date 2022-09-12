package com.windula.demoetl.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalStore {
    // Using ThreadLocal variables with executor service thread model considered as mal practise unless the
    // the thread model is highly (properly) controlled. When applying any changes here or thread executor service consider this fact as well.
    // For more information : https://www.baeldung.com/java-threadlocal
    /**
     * Prevent constructing objects of this class.
     * By declaring this private constructor.
     */
    private ThreadLocalStore() {
    }
    private static final ThreadLocal<Map<String, Object>> copyOnThreadLocal = ThreadLocal.withInitial(HashMap::new);
    /**
     * Put item to thread local Hash Map.
     *
     * @param key the key
     * @param val the val
     */
    public static void put(String key, Object val) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (val == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        Map<String, Object> storeMap = copyOnThreadLocal.get();
        storeMap.put(key, val);
    }
    /**
     * Get object from thread local Hash Map
     *
     * @param key the key
     * @return the object
     */
    public static Object get(String key) {
        final Map<String, Object> map = copyOnThreadLocal.get();
        return map.get(key);
    }
}

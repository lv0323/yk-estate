package com.lyun.estate.core.supports.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseContext {

    private static final int WRITE_OPERATION = 1;
    private static final int MAP_COPY_OPERATION = 2;

    private final ThreadLocal<Map<String, Object>> copyOnThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<Integer> lastOperation = new ThreadLocal<>();

    private Integer getAndSetLastOperation(int op) {
        Integer lastOp = lastOperation.get();
        lastOperation.set(op);
        return lastOp;
    }

    private boolean wasLastOpReadOrNull(Integer lastOp) {
        return lastOp == null || lastOp == MAP_COPY_OPERATION;
    }

    private Map<String, Object> duplicateAndInsertNewMap(Map<String, Object> oldMap) {
        Map<String, Object> newMap = Collections.synchronizedMap(new HashMap<String, Object>());
        if (oldMap != null) {
            synchronized (oldMap) {
                newMap.putAll(oldMap);
            }
        }
        copyOnThreadLocal.set(newMap);
        return newMap;
    }

    protected void put(String key, Object val) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        Map<String, Object> oldMap = copyOnThreadLocal.get();
        Integer lastOp = getAndSetLastOperation(WRITE_OPERATION);

        if (wasLastOpReadOrNull(lastOp) || oldMap == null) {
            Map<String, Object> newMap = duplicateAndInsertNewMap(oldMap);
            newMap.put(key, val);
        } else {
            oldMap.put(key, val);
        }
    }

    protected void remove(String key) {
        if (key == null) {
            return;
        }
        Map<String, Object> oldMap = copyOnThreadLocal.get();
        if (oldMap == null)
            return;

        Integer lastOp = getAndSetLastOperation(WRITE_OPERATION);

        if (wasLastOpReadOrNull(lastOp)) {
            Map<String, Object> newMap = duplicateAndInsertNewMap(oldMap);
            newMap.remove(key);
        } else {
            oldMap.remove(key);
        }
    }

    public void clear() {
        lastOperation.set(WRITE_OPERATION);
        copyOnThreadLocal.remove();
    }


    protected Object get(String key) {
        final Map<String, Object> map = copyOnThreadLocal.get();
        if ((map != null) && (key != null)) {
            return map.get(key);
        } else {
            return null;
        }
    }
}

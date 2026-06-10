package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.Cacheable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kzlv4natoly
 */
public class CacheableInvocationHandler implements InvocationHandler {

    private final Object target;
    private final Map<Method, Map<Object, Object>> cache = new HashMap<>();

    public CacheableInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class)) {
            Object arg = (args != null && args.length > 0) ? args[0] : null;
            Map<Object, Object> methodCache = cache.computeIfAbsent(method, k -> new HashMap<>());
            if (methodCache.containsKey(arg)) {
                return methodCache.get(arg);
            }

            try {
                Object result = method.invoke(target, args);
                methodCache.put(arg, result);
                return result;
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }

        try {
            return method.invoke(target, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
package edu.phystech.hw5.service;

import java.lang.reflect.Proxy;

/**
 * @author kzlv4natoly
 */
public class CacheUtils {
    @SuppressWarnings("unchecked")
    public static <T> T getCacheProxy(Class<T> clazz, T object) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new CacheableInvocationHandler(object));
    }
}

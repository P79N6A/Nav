package zy.nav;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.LruCache;

import java.lang.reflect.Constructor;
import java.util.Collection;

final class Utils {

    static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    static void requireUrlNotEmpty(String url) {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is null or empty");
        }
    }

    @Nullable
    @SuppressWarnings("unchecked")
    static <T> T newInstance(String className, LruCache<String, Constructor<?>> cache) {
        if (TextUtils.isEmpty(className)) {
            return null;
        }
        Constructor<?> constructor = cache.get(className);
        if (constructor == null) {
            constructor = getConstructor(className);
            if (constructor == null) {
                return null;
            }
            cache.put(className, constructor);
        }
        try {
            return (T) constructor.newInstance();
        } catch (Exception e) {
            Logger.e("error newInstance ---> " + className);
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    static <T> Constructor<T> getConstructor(String className, Class<?>... paramTypes) {
        if (TextUtils.isEmpty(className)) {
            return null;
        }
        try {
            Class<?> clazz = Class.forName(className);
            return (Constructor<T>) clazz.getConstructor(paramTypes);
        } catch (Exception e) {
            Logger.e("error getDefaultConstructor ---> " + className);
        }
        return null;
    }

}

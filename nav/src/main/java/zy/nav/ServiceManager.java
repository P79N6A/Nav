package zy.nav;

import android.util.LruCache;

import java.lang.reflect.Constructor;

final class ServiceManager {

    private static final LruCache<String, Constructor<?>> CACHE = new LruCache<>(66);

    static <T> T getService(Class<T> serviceClass) {
        return Utils.newInstance(DistributionCenter.serviceIndex.get(serviceClass.getName()), CACHE);
    }

    static <T> T getService(String uri) {
        return Utils.newInstance(DistributionCenter.serviceIndex.get(uri), CACHE);
    }
}

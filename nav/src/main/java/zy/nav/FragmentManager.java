package zy.nav;

import android.util.LruCache;

import java.lang.reflect.Constructor;

final class FragmentManager {

    private static final LruCache<String, Constructor<?>> CACHE = new LruCache<>(66);

    static <T> T getService(String uri) {
        return Utils.newInstance(DistributionCenter.fragmentIndex.get(uri), CACHE);
    }
}

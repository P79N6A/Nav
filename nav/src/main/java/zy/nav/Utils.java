package zy.nav;

import java.util.Collection;

final class Utils {

    static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

}

package zy.nav;

import android.net.Uri;

import java.util.Collection;

final class Utils {

    static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    static boolean checkUri(Uri uri) {
        return uri != null && uri.isHierarchical();
    }

}

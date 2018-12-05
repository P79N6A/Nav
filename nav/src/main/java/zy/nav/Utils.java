package zy.nav;

final class Utils {

    static void requireNonNull(Object obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }

}

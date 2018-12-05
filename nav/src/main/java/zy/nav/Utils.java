package zy.nav;

final class Utils {

    static void requireNonNull(Object obj, String msg) {
        assertAndThrow(obj == null, msg);
    }

    static void assertAndThrow(boolean established, String msg) {
        if (established) {
            return;
        }
        throw new RuntimeException(msg);
    }

}

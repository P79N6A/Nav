package zy.nav;

import android.util.Log;

public class Logger {

    public static void e(String msg) {
        if (NavConfig.getInstance().isDebug()) {
            Log.e("Nav", msg);
        }
    }

}

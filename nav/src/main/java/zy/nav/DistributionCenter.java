package zy.nav;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

final class DistributionCenter {

    static final Map<String, String> activityIndex = new HashMap<>();

    static final Map<String, String> fragmentIndex = new HashMap<>();

    static final Map<String, String> serviceIndex = new HashMap<>();

    @SuppressWarnings("unused")
    private static void register(String className) {
        if (TextUtils.isEmpty(className)) {
            return;
        }
        try {
            Object obj = Class.forName(className)
                    .getConstructor()
                    .newInstance();
            if (obj instanceof ActivityRegister) {
                register((ActivityRegister) obj);
            } else if (obj instanceof ServiceRegister) {
                register((ServiceRegister) obj);
            } else if (obj instanceof FragmentRegister) {
                register((FragmentRegister) obj);
            } else {
                Logger.e(className + " register failed");
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    private static void register(ActivityRegister register) {
        if (register != null) {
            register.register(activityIndex);
        }
    }

    private static void register(FragmentRegister register) {
        if (register != null) {
            register.register(fragmentIndex);
        }
    }

    private static void register(ServiceRegister register) {
        if (register != null) {
            register.register(serviceIndex);
        }
    }

}

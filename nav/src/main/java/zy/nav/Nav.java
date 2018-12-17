package zy.nav;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

public final class Nav {

    public static final String RAW_URI = "__Nav__Raw__Uri__";

    private static final int NO_REQUEST_CODE = -1;

    private final NavDelegate delegate;

    private Nav(Initiator initiator) {
        this.delegate = new NavDelegate(initiator);
    }

    public static Nav from(Context context) {
        return from(Initiator.Factory.from(context));
    }

    public static Nav from(Activity activity) {
        return from(Initiator.Factory.from(activity));
    }

    public static Nav from(Fragment fragment) {
        return from(Initiator.Factory.from(fragment));
    }

    private static Nav from(Initiator initiator) {
        return new Nav(initiator);
    }

    @Nullable
    public static <T> T getService(@NonNull Class<T> serviceClass) {
        return NavDelegate.getService(serviceClass);
    }

    @Nullable
    public static <T> T getService(@NonNull String uri) {
        return NavDelegate.getService(uri);
    }

    public void to(String url) {
        delegate.to(url, NO_REQUEST_CODE);
    }

    public void to(String url, @IntRange(from = 0) int requestCode) {
        delegate.to(url, requestCode);
    }

    public Nav addFlag(int flag) {
        delegate.addFlag(flag);
        return this;
    }

    public Nav withOptions(ActivityOptionsCompat options) {
        delegate.withOptions(options);
        return this;
    }

    public Nav withInterceptor(@NonNull Interceptor interceptor) {
        delegate.withInterceptor(interceptor);
        return this;
    }

    public Nav withAll(@NonNull Bundle bundle) {
        delegate.withAll(bundle);
        return this;
    }

    public Nav withBoolean(String key, boolean bundle) {
        delegate.withBoolean(key, bundle);
        return this;
    }

    public Nav withByte(String key, byte value) {
        delegate.withByte(key, value);
        return this;
    }

    public Nav withChar(String key, char value) {
        delegate.withChar(key, value);
        return this;
    }

    public Nav withShort(String key, short value) {
        delegate.withShort(key, value);
        return this;
    }

    public Nav withInt(String key, int value) {
        delegate.withInt(key, value);
        return this;
    }

    public Nav withLong(String key, long value) {
        delegate.withLong(key, value);
        return this;
    }

    public Nav withFloat(String key, float value) {
        delegate.withFloat(key, value);
        return this;
    }

    public Nav withDouble(String key, double value) {
        delegate.withDouble(key, value);
        return this;
    }

    public Nav withString(String key, String value) {
        delegate.withString(key, value);
        return this;
    }

    public Nav withObject(String key, Object value) {
        delegate.withObject(key, value);
        return this;
    }

}

package zy.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

public final class Nav {

    public static final String RAW_URI = "__Nav__Raw__Uri__";

    private final NavDelegate delegate;

    private Nav(Initiator initiator) {
        this.delegate = new NavDelegate(initiator);
    }

    public static Nav from(Context context) {
        return create(Initiator.Factory.from(context));
    }

    public static Nav from(Activity activity) {
        return create(Initiator.Factory.from(activity));
    }

    public static Nav from(Fragment fragment) {
        return create(Initiator.Factory.from(fragment));
    }

    private static Nav create(Initiator initiator) {
        return new Nav(initiator);
    }

    public void to(String url) {
        Utils.assertAndThrow(TextUtils.isEmpty(url),
                "url is empty");
        to(Uri.parse(url));
    }

    public void to(Uri uri) {
        Utils.requireNonNull(uri, "uri == null");
        Utils.assertAndThrow(!uri.isHierarchical(),
                "This isn't a hierarchical URI.");
        delegate.to(uri);
    }

    public void to(String url, int requestCode) {
        Utils.assertAndThrow(TextUtils.isEmpty(url),
                "url is empty");
        to(Uri.parse(url), requestCode);
    }

    public void to(Uri uri, int requestCode) {
        Utils.requireNonNull(uri, "uri == null");
        Utils.assertAndThrow(!uri.isHierarchical(),
                "This isn't a hierarchical URI.");
        delegate.to(uri, requestCode);
    }

    public Intent resolve(String url) {
        Utils.assertAndThrow(TextUtils.isEmpty(url),
                "url is empty");
        return resolve(Uri.parse(url));
    }

    public Intent resolve(Uri uri) {
        Utils.requireNonNull(uri, "uri == null");
        Utils.assertAndThrow(!uri.isHierarchical(),
                "This isn't a hierarchical URI.");
        return delegate.resolve(uri);
    }

    public Nav withInterceptor(@NonNull Interceptor interceptor) {
        delegate.withInterceptor(interceptor);
        return this;
    }

    public Nav withAll(@NonNull Bundle bundle) {
        delegate.withAll(bundle);
        return this;
    }

    public Nav withBundle(String key, Bundle bundle) {
        delegate.withBundle(key, bundle);
        return this;
    }

    public Nav withBoolean(String key, boolean bundle) {
        delegate.withBoolean(key, bundle);
        return this;
    }

    public Nav withBooleanArray(String key, boolean[] bundle) {
        delegate.withBooleanArray(key, bundle);
        return this;
    }

    public Nav withByte(String key, byte value) {
        delegate.withByte(key, value);
        return this;
    }

    public Nav withByteArray(String key, byte[] value) {
        delegate.withByteArray(key, value);
        return this;
    }

    public Nav withChar(String key, char value) {
        delegate.withChar(key, value);
        return this;
    }

    public Nav withCharArray(String key, char[] value) {
        delegate.withCharArray(key, value);
        return this;
    }

    public Nav withShort(String key, short value) {
        delegate.withShort(key, value);
        return this;
    }

    public Nav withShortArray(String key, short[] value) {
        delegate.withShortArray(key, value);
        return this;
    }

    public Nav withInt(String key, int value) {
        delegate.withInt(key, value);
        return this;
    }

    public Nav withIntArray(String key, int[] value) {
        delegate.withIntArray(key, value);
        return this;
    }

    public Nav withIntegerArrayList(String key, ArrayList<Integer> value) {
        delegate.withIntegerArrayList(key, value);
        return this;
    }

    public Nav withLong(String key, long value) {
        delegate.withLong(key, value);
        return this;
    }

    public Nav withLongArray(String key, long[] value) {
        delegate.withLongArray(key, value);
        return this;
    }

    public Nav withFloat(String key, float value) {
        delegate.withFloat(key, value);
        return this;
    }

    public Nav withFloatArray(String key, float[] value) {
        delegate.withFloatArray(key, value);
        return this;
    }

    public Nav withDouble(String key, double value) {
        delegate.withDouble(key, value);
        return this;
    }

    public Nav withDoubleArray(String key, double[] value) {
        delegate.withDoubleArray(key, value);
        return this;
    }

    public Nav withString(String key, String value) {
        delegate.withString(key, value);
        return this;
    }

    public Nav withStringArray(String key, String[] value) {
        delegate.withStringArray(key, value);
        return this;
    }

    public Nav withStringArrayList(String key, ArrayList<String> value) {
        delegate.withStringArrayList(key, value);
        return this;
    }

    public Nav withCharSequence(String key, CharSequence value) {
        delegate.withCharSequence(key, value);
        return this;
    }

    public Nav withCharSequenceArray(String key, CharSequence[] value) {
        delegate.withCharSequenceArray(key, value);
        return this;
    }

    public Nav withCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
        delegate.withCharSequenceArrayList(key, value);
        return this;
    }

    public Nav withParcelable(String key, Parcelable value) {
        delegate.withParcelable(key, value);
        return this;
    }

    public Nav withParcelableArray(String key, Parcelable[] value) {
        delegate.withParcelableArray(key, value);
        return this;
    }

    public Nav withParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
        delegate.withParcelableArrayList(key, value);
        return this;
    }

    public Nav withSparseParcelableArray(String key, SparseArray<? extends Parcelable> value) {
        delegate.withSparseParcelableArray(key, value);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Nav withSize(String key, Size value) {
        delegate.withSize(key, value);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Nav withSizeF(String key, SizeF value) {
        delegate.withSizeF(key, value);
        return this;
    }

    public Nav withSerializable(String key, Serializable value) {
        delegate.withSerializable(key, value);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Nav withBinder(String key, IBinder value) {
        delegate.withBinder(key, value);
        return this;
    }

}

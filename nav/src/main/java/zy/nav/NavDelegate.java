package zy.nav;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

final class NavDelegate {

    private final Initiator initiator;

    private final Request request;

    private final List<Interceptor> interceptorList;

    NavDelegate(Initiator initiator) {
        this.initiator = initiator;
        this.request = Request.create();
        this.request.context(initiator.getContext());
        interceptorList = new ArrayList<>();
    }

    void to(Uri uri) {
        beforeTo(uri);
        Intent intent = resolve(uri);
        if (intent != null) {
            initiator.startActivity(intent);
        }
    }

    void to(Uri uri, int requestCode) {
        beforeTo(uri);
        request.requestCode(requestCode);
        Intent intent = resolve(uri);
        if (intent != null) {
            initiator.startActivityForResult(intent, requestCode);
        }
    }

    Intent resolve(Uri uri) {
        beforeTo(uri);
        Response response = NavCall.newCall(interceptorList, request).call();
        return response.intent();
    }

    private void beforeTo(Uri uri) {
        request.uri(uri);
        withString(Nav.RAW_URI, uri.toString());
        Set<String> names = uri.getQueryParameterNames();
        for (String name : names) {
            withString(name, uri.getQueryParameter(name));
        }
    }

    void withInterceptor(Interceptor interceptor) {
        interceptorList.add(interceptor);
    }

    void withAll(Bundle bundle) {
        request.params().putAll(bundle);
    }

    void withBundle(String key, Bundle bundle) {
        request.params().putBundle(key, bundle);
    }

    void withBoolean(String key, boolean value) {
        request.params().putBoolean(key, value);
    }

    void withBooleanArray(String key, boolean[] value) {
        request.params().putBooleanArray(key, value);
    }

    void withByte(String key, byte value) {
        request.params().putByte(key, value);
    }

    void withByteArray(String key, byte[] value) {
        request.params().putByteArray(key, value);
    }

    void withChar(String key, char value) {
        request.params().putChar(key, value);
    }

    void withCharArray(String key, char[] value) {
        request.params().putCharArray(key, value);
    }

    void withShort(String key, short value) {
        request.params().putShort(key, value);
    }

    void withShortArray(String key, short[] value) {
        request.params().putShortArray(key, value);
    }

    void withInt(String key, int value) {
        request.params().putInt(key, value);
    }

    void withIntArray(String key, int[] value) {
        request.params().putIntArray(key, value);
    }

    void withIntegerArrayList(String key, ArrayList<Integer> value) {
        request.params().putIntegerArrayList(key, value);
    }

    void withLong(String key, long value) {
        request.params().putLong(key, value);
    }

    void withLongArray(String key, long[] value) {
        request.params().putLongArray(key, value);
    }

    void withFloat(String key, float value) {
        request.params().putFloat(key, value);
    }

    void withFloatArray(String key, float[] value) {
        request.params().putFloatArray(key, value);
    }

    void withDouble(String key, double value) {
        request.params().putDouble(key, value);
    }

    void withDoubleArray(String key, double[] value) {
        request.params().putDoubleArray(key, value);
    }

    void withString(String key, String value) {
        request.params().putString(key, value);
    }

    void withStringArray(String key, String[] value) {
        request.params().putStringArray(key, value);
    }

    void withStringArrayList(String key, ArrayList<String> value) {
        request.params().putStringArrayList(key, value);
    }

    void withCharSequence(String key, CharSequence value) {
        request.params().putCharSequence(key, value);
    }

    void withCharSequenceArray(String key, CharSequence[] value) {
        request.params().putCharSequenceArray(key, value);
    }

    void withCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
        request.params().putCharSequenceArrayList(key, value);
    }

    void withParcelable(String key, Parcelable value) {
        request.params().putParcelable(key, value);
    }

    void withParcelableArray(String key, Parcelable[] value) {
        request.params().putParcelableArray(key, value);
    }

    void withParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
        request.params().putParcelableArrayList(key, value);
    }

    void withSparseParcelableArray(String key, SparseArray<? extends Parcelable> value) {
        request.params().putSparseParcelableArray(key, value);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void withSize(String key, Size value) {
        request.params().putSize(key, value);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void withSizeF(String key, SizeF value) {
        request.params().putSizeF(key, value);
    }

    void withSerializable(String key, Serializable value) {
        request.params().putSerializable(key, value);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    void withBinder(String key, IBinder value) {
        request.params().putBinder(key, value);
    }
}

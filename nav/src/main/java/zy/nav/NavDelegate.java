package zy.nav;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

import java.util.ArrayList;
import java.util.List;

final class NavDelegate {

    private final Initiator initiator;

    private final Request request;

    private final List<Interceptor> interceptorList;

    NavDelegate(Initiator initiator) {
        this.initiator = initiator;
        this.request = Request.newRequest();
        interceptorList = new ArrayList<>();
    }

    void to(String url, int requestCode) {
        request.url(url);
        request.requestCode(requestCode);
        NavCall.newCall(interceptorList, request, initiator).call();
    }

    static <T> T getService(Class<T> serviceClass) {
        return ServiceManager.getService(serviceClass);
    }

    static <T> T getService(String uri) {
        return ServiceManager.getService(uri);
    }

    void addFlag(int flag) {
        request.addFlag(flag);
    }

    void withOptions(ActivityOptionsCompat options) {
        request.options(options);
    }

    void withInterceptor(Interceptor interceptor) {
        interceptorList.add(interceptor);
    }

    void withAll(Bundle bundle) {
        request.params().putAll(bundle);
    }

    void withBoolean(String key, boolean value) {
        request.params().putBoolean(key, value);
    }

    void withByte(String key, byte value) {
        request.params().putByte(key, value);
    }

    void withChar(String key, char value) {
        request.params().putChar(key, value);
    }

    void withShort(String key, short value) {
        request.params().putShort(key, value);
    }

    void withInt(String key, int value) {
        request.params().putInt(key, value);
    }

    void withLong(String key, long value) {
        request.params().putLong(key, value);
    }

    void withFloat(String key, float value) {
        request.params().putFloat(key, value);
    }

    void withDouble(String key, double value) {
        request.params().putDouble(key, value);
    }

    void withString(String key, String value) {
        request.params().putString(key, value);
    }

    void withObject(String key, Object value) {
        JsonMarshaller marshaller = Nav.getService(JsonMarshaller.class);
        if (marshaller != null) {
            request.params().putString(key, marshaller.toJson(value));
        }
    }
}

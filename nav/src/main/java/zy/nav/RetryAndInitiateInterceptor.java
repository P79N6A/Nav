package zy.nav;

import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;

import java.util.Set;

import zy.nav.exception.RetryException;

class RetryAndInitiateInterceptor implements Interceptor {

    private static final int MAX_RETRY_COUNT = 2;

    private int count;

    private final Initiator initiator;

    private final boolean autoSkip;

    RetryAndInitiateInterceptor(Initiator initiator, boolean autoSkip) {
        this.initiator = initiator;
        this.autoSkip = autoSkip;
        this.count = 0;
    }

    @Override
    public Response intercept(Chain chain) {
        if (++count > MAX_RETRY_COUNT) {
            throw new RetryException("retry count -> " + MAX_RETRY_COUNT);
        }
        Request request = chain.request();
        Uri uri = request.uri();
        Set<String> names = uri.getQueryParameterNames();
        if (names != null && !names.isEmpty()) {
            for (String name : names) {
                request.params().putString(name, uri.getQueryParameter(name));
            }
        }
        Response response = chain.process(request);
        if (response.success() && autoSkip) {
            ActivityOptionsCompat optionsCompat = request.options();
            initiator.startActivityForResult(response.intent(),
                    request.requestCode(), optionsCompat == null ? null : optionsCompat.toBundle());
        }
        return response;
    }
}

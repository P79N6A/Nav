package zy.nav;

import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;

import java.util.Set;

import zy.nav.exception.NavException;

class RetryAndInitiateInterceptor implements Interceptor {

    private static final int MAX_RETRY_COUNT = 1;

    private int count;

    private final Initiator initiator;

    RetryAndInitiateInterceptor(Initiator initiator) {
        this.initiator = initiator;
        this.count = 0;
    }

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        String url = request.url();
        Utils.requireUrlNotEmpty(url);
        if (++count > MAX_RETRY_COUNT + 1) {
            throw new NavException("retry count is "
                    + MAX_RETRY_COUNT + "time,And it's exhausted.");
        }
        Uri uri = Uri.parse(url);
        Set<String> names = uri.getQueryParameterNames();
        if (names != null && !names.isEmpty()) {
            for (String name : names) {
                request.params().putString(name, uri.getQueryParameter(name));
            }
        }
        Response response = chain.process(request);
        if (response.success()) {
            ActivityOptionsCompat optionsCompat = request.options();
            initiator.startActivityForResult(response.intent(),
                    request.requestCode(), optionsCompat == null ? null : optionsCompat.toBundle());
        }
        return response;
    }
}

package zy.nav;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;

import zy.nav.exception.InterceptException;
import zy.nav.exception.RedirectException;

public final class Request {

    private Uri uri;

    private int requestCode;

    private Bundle params;

    private int flags;

    private ActivityOptionsCompat options;

    private Request() {
        this.params = new Bundle();
    }

    static Request newRequest() {
        return new Request();
    }

    void uri(Uri uri) {
        this.uri = uri;
    }

    public Uri uri() {
        return uri;
    }

    public Bundle params() {
        return params;
    }

    void options(ActivityOptionsCompat options) {
        this.options = options;
    }

    public ActivityOptionsCompat options() {
        return options;
    }

    void requestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int requestCode() {
        return requestCode;
    }

    void addFlag(int flag) {
        this.flags |= flag;
    }

    public int flags() {
        return this.flags;
    }

    public void intercept(String msg) {
        throw new InterceptException(msg);
    }

    public void redirect(String url) {
        redirect(Uri.parse(TextUtils.isEmpty(url) ? "" : url));
    }

    public void redirect(Uri uri) {
        uri(uri);
        throw new RedirectException("Redirect -> " + uri.toString());
    }
}

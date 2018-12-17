package zy.nav;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

import zy.nav.exception.NavException;
import zy.nav.exception.RedirectException;

public final class Request {

    private String url;

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

    void url(String url) {
        this.url = url;
    }

    public String url() {
        return url;
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
        throw new NavException(msg);
    }

    public void redirect(String url) {
        url(url);
        throw new RedirectException("Redirect -> " + url);
    }
}

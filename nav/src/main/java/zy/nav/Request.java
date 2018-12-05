package zy.nav;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

public final class Request {

    private Context context;

    private Uri uri;

    private int requestCode;

    private Bundle params;

    private Request() {
        this.params = new Bundle();
    }

    static Request create() {
        return new Request();
    }

    void context(Context context) {
        this.context = context;
    }

    Context context() {
        return context;
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

    void requestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int requestCode() {
        return requestCode;
    }
}

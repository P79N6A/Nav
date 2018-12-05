package zy.nav;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public final class Request {

    final Initiator initiator;

    private Uri uri;

    private int requestCode;

    Bundle params;

    private Request(Initiator initiator) {
        this.initiator = initiator;
        this.params = new Bundle();
    }

    private static Request create(Initiator initiator) {
        return new Request(initiator);
    }

    static Request create(Context context) {
        return create(Initiator.Factory.from(context));
    }

    static Request create(Activity activity) {
        return create(Initiator.Factory.from(activity));
    }

    static Request create(Fragment fragment) {
        return create(Initiator.Factory.from(fragment));
    }

    void uri(Uri uri) {
        this.uri = uri;
    }

    public Uri uri() {
        return uri;
    }

    void requestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int requestCode() {
        return requestCode;
    }
}

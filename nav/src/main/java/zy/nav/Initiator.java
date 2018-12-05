package zy.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * 路由发起者
 * 因为Fragment#startActivityForResult用宿主Activity来发起的话，走不到Fragment里的onActivityResult方法，
 * 所以抽取该接口
 */
interface Initiator {

    Context getContext();

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int requestCode);

    class Factory {

        static Initiator from(Context context) {
            return new NavContext(context);
        }

        static Initiator from(Activity activity) {
            return new NavActivity(activity);
        }

        static Initiator from(Fragment fragment) {
            return new NavFragment(fragment);
        }
    }

    class NavContext implements Initiator {

        final Context context;

        NavContext(Context context) {
            Utils.requireNonNull(context, "context must not be null");
            this.context = context;
        }

        @Override
        public Context getContext() {
            return context;
        }

        @Override
        public void startActivity(Intent intent) {
            if (!Activity.class.isInstance(context)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode) {
            throw new IllegalArgumentException("unsupported call startActivityForResult on "
                    + context.getClass().getName());
        }
    }

    class NavActivity extends NavContext {

        NavActivity(Activity activity) {
            super(activity);
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

    class NavFragment extends NavContext {

        final Fragment fragment;

        NavFragment(Fragment fragment) {
            super(fragment.getContext());
            this.fragment = fragment;
        }

        @Override
        public void startActivity(Intent intent) {
            fragment.startActivity(intent);
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode) {
            fragment.startActivityForResult(intent, requestCode);
        }
    }

}

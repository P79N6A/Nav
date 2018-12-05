package zy.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

interface NavHost {

    Context getContext();

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int requestCode);

    class Factory {

        static NavHost from(Context context) {
            return new HostContext(context);
        }

        static NavHost from(Activity activity) {
            return new HostActivity(activity);
        }

        static NavHost from(Fragment fragment) {
            return new HostFragment(fragment);
        }
    }

    class HostContext implements NavHost {

        final Context context;

        HostContext(Context context) {
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

    class HostActivity extends HostContext {

        HostActivity(Activity activity) {
            super(activity);
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

    class HostFragment extends HostContext {

        final Fragment fragment;

        HostFragment(Fragment fragment) {
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

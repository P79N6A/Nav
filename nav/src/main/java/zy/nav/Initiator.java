package zy.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

interface Initiator {

    Context context();

    void startActivityForResult(Intent intent, int requestCode, Bundle option);

    class Factory {

        static Initiator from(Context context) {
            if (context instanceof Activity) {
                return from((Activity) context);
            }
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

        @Override
        public Context context() {
            return context;
        }

        NavContext(Context context) {
            if (context == null) {
                throw new NullPointerException("context == null");
            }
            this.context = context;
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode, Bundle option) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    class NavActivity extends NavContext {

        NavActivity(Activity activity) {
            super(activity);
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode, Bundle option) {
            Activity activity = (Activity) context;
            if (option != null) {
                activity.startActivityForResult(intent, requestCode, option);
            } else {
                activity.startActivityForResult(intent, requestCode);
            }
        }
    }

    class NavFragment extends NavContext {

        final Fragment fragment;

        NavFragment(Fragment fragment) {
            super(fragment.getContext());
            this.fragment = fragment;
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode, Bundle option) {
            if (option != null) {
                fragment.startActivityForResult(intent, requestCode, option);
            } else {
                fragment.startActivityForResult(intent, requestCode);
            }
        }
    }

}

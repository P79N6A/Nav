package zy.nav;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

class SystemFindInterceptor implements Interceptor {

    private final PackageManager manager;

    private final String packageName;

    SystemFindInterceptor(Context context) {
        this.manager = context.getPackageManager();
        this.packageName = context.getPackageName();
    }

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        Response response = chain.process(request);
        if (!response.success()) {
            Intent intent = response.intent();
            intent.setPackage(packageName);
            intent.setData(request.uri());
            intent.setAction(Intent.ACTION_VIEW);
            ComponentName componentName = intent.resolveActivity(manager);
            if (componentName != null) {
                intent.setComponent(componentName);
                response.success(true);
            }
        }
        return response;
    }

}

package zy.nav;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

class RouteFindInterceptor implements Interceptor {

    private final String packageName;

    RouteFindInterceptor(Context context) {
        this.packageName = context.getPackageName();
    }

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        String url = request.url();
        String activityClassName = DistributionCenter.activityIndex.get(url);
        boolean isSuccess = activityClassName != null && activityClassName.length() > 0;
        Response response = Response.newResponse();
        response.success(isSuccess);
        if (isSuccess) {
            Intent intent = response.intent();
            ComponentName componentName = new ComponentName(packageName, activityClassName);
            intent.setComponent(componentName);
            response.message("success");
        } else {
            response.message("failed,Did not find activity class by RouteFindInterceptor.");
        }
        return response;
    }

}

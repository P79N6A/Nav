package zy.nav;

class RouteFindInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) {
        return Response.failure("not implement,wait for a moment");
    }

}

package zy.nav;

public interface Interceptor {

    Response intercept(Chain chain);

    interface Chain {

        Request request();

        Response process(Request request);

    }

}

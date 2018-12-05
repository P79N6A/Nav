package zy.nav;

import java.util.List;
import java.util.concurrent.Callable;

public class NavCall implements Callable<Response> {

    private final Request request;

    private final List<Interceptor> interceptorList;

    private NavCall(List<Interceptor> interceptorList, Request request) {
        this.interceptorList = interceptorList;
        this.request = request;
    }

    static NavCall newCall(List<Interceptor> interceptorList, Request request) {
        return new NavCall(interceptorList, request);
    }

    @Override
    public Response call() {
        WorkChain chain = new WorkChain(interceptorList, request, 0);
        return chain.process(request);
    }

}

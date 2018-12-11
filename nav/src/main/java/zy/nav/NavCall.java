package zy.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import zy.nav.exception.InterceptException;
import zy.nav.exception.NavException;
import zy.nav.exception.RedirectException;
import zy.nav.exception.RetryException;

class NavCall implements Callable<Response> {

    private final Request request;

    private final List<Interceptor> interceptorList;

    private final Initiator initiator;

    private final RetryAndInitiateInterceptor retryAndInitiateInterceptor;

    private NavCall(List<Interceptor> interceptorList, Request request, Initiator initiator) {
        this.interceptorList = interceptorList;
        this.request = request;
        this.initiator = initiator;
        this.retryAndInitiateInterceptor = new RetryAndInitiateInterceptor(initiator);
    }

    static NavCall newCall(List<Interceptor> interceptorList, Request request, Initiator initiator) {
        return new NavCall(interceptorList, request, initiator);
    }

    @Override
    public Response call() {
        List<Interceptor> list = new ArrayList<>();
        list.add(retryAndInitiateInterceptor);
        if (!Utils.isEmpty(interceptorList)) {
            list.addAll(interceptorList);
        }
        list.add(new SystemFindInterceptor(initiator.context()));
        list.add(new RouteFindInterceptor());
        Interceptor.Chain chain = new InterceptorChain(list, request, 0);
        try {
            return chain.process(request);
        } catch (NavException e) {
            if (e instanceof RetryException) {
                return Response.failure(e.getMessage());
            }
            if (e instanceof InterceptException) {
                return Response.failure(e.getMessage());
            }
            if (e instanceof RedirectException) {
                return call();
            }
        }
        return Response.failure("unknown");
    }

}

package zy.nav;

import java.util.ArrayList;
import java.util.List;

import zy.nav.exception.RedirectException;

final class NavCall {

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

    final Response call() {
        List<Interceptor> list = new ArrayList<>();
        list.add(retryAndInitiateInterceptor);
        if (!Utils.isEmpty(interceptorList)) {
            list.addAll(interceptorList);
        }
        list.add(new SystemFindInterceptor(initiator.context()));
        list.add(new RouteFindInterceptor(initiator.context()));
        Interceptor.Chain chain = new InterceptorChain(list, request, 0);
        try {
            return chain.process(request);
        } catch (Exception e) {
            if (e instanceof RedirectException) {
                return call();
            }
            return Response.failure(e.getMessage());
        }
    }

}

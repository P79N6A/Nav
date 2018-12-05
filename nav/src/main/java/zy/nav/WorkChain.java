package zy.nav;

import java.util.List;

final class WorkChain implements Interceptor.Chain {

    private final List<Interceptor> interceptorList;

    private final Request request;

    private final int index;

    WorkChain(List<Interceptor> interceptorList, Request request, int index) {
        this.interceptorList = interceptorList;
        this.request = request;
        this.index = index;
    }

    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response process(Request request) {
        if (index >= interceptorList.size()) {
            throw new AssertionError("index out");
        }
        WorkChain next = new WorkChain(interceptorList, request, index + 1);
        Interceptor interceptor = interceptorList.get(index);
        return interceptor.intercept(next);
    }
}

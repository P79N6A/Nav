package zy.nav;

import java.util.List;

final class WorkChain implements Interceptor.Chain {

    final List<Interceptor> interceptorList;

    final Request request;

    final int index;

    public WorkChain(List<Interceptor> interceptorList, Request request, int index) {
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
        if (index + 1 >= interceptorList.size() - 1) {

        }
        return null;
    }
}

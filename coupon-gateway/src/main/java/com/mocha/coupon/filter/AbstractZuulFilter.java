package com.mocha.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <h1>通用的抽象过滤器类</h1>
 */
public abstract class AbstractZuulFilter extends ZuulFilter {

    //用于在过滤器之间传递消息，数据保存在每个请求的ThreadLocal中
    //扩展了map
    RequestContext context;

    private final static String NEXT = "next";

    @Override
    public boolean shouldFilter() {

        RequestContext rtx = RequestContext.getCurrentContext();
        return (boolean)rtx.getOrDefault(NEXT,true);
    }

    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return null;
    }

    protected abstract Object cRun();

    //失败返回的内容
    Object fail(int code,String msg){
        context.set(NEXT,false);
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format(
                "{\"result\":\"%s!\"},",msg
        ));
        return null;
    }

    //成功返回的内容
    Object success(){
        context.set(NEXT,true);
        return null;
    }
}

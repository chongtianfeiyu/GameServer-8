package com.itgowo.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;

/**
 * Created by hnvfh on 2017/3/28.
 * http服务器
 */
public class HttpServerInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
    private HttpRequest mHttpRequest;
    private HttpContent mHttpContent;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            mHttpRequest = (HttpRequest) msg;
        }
        if (msg instanceof HttpContent) {
            mHttpContent = (HttpContent) msg;
        }
    }

    /**
     * 设置HTTP返回头信息
     */
    private void setHeaders(FullHttpResponse response) {
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
//        if (HttpHeaders.isKeepAlive(request)) {
//            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
//        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        if (mHttpRequest != null && mHttpContent != null) {
            HttpServerManager.onReceiveHandleFirst(ctx, mHttpRequest, mHttpContent);
        }
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

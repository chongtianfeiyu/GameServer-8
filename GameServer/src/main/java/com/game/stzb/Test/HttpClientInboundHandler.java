package com.game.stzb.Test;


import com.itgowo.SimpleServerCore.Http.HttpServerHandler;
import com.itgowo.SimpleServerCore.Utils.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;

public class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {
    private HttpRequest request;
    private HttpResponse response;
    private HttpServerHandler.onReceiveHandlerListener onReceiveHandlerListener;
    private byte[] bytes = new byte[0];

    public HttpClientInboundHandler setOnReceiveHandlerListener(HttpServerHandler.onReceiveHandlerListener onReceiveHandlerListener) {
        this.onReceiveHandlerListener = onReceiveHandlerListener;
        return this;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest) {
            request = (HttpRequest) msg;
        }
        if (msg instanceof HttpResponse) {
            response = (HttpResponse) msg;
        }
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            byte[] b = new byte[buf.readableBytes()];
            buf.getBytes(0, b);
            this.bytes = Utils.append(this.bytes, b);
//            buf.release();
        }
        if (msg instanceof LastHttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            byte[] b = new byte[buf.readableBytes()];
            buf.getBytes(0, b);
            this.bytes = Utils.append(this.bytes, b);
//            buf.release();
            if (onReceiveHandlerListener != null) {
                onReceiveHandlerListener.onReceiveHandler(new HttpServerHandler(ctx, request, response, bytes));
            }
            bytes = new byte[0];
        }
    }


}
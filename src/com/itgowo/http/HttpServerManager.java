package com.itgowo.http;

import com.game.stzb.GameServer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hnvfh on 2017/5/25.
 */
public class HttpServerManager {
    private static Logger log = LogU.getLogU("com.itgowo.http.HttpServerManager", Level.ALL);

    /**
     * 业务处理逻辑第一个方法，分发业务处理逻辑
     */
    public static void onReceiveHandleFirst(ChannelHandlerContext ctx, HttpRequest mHttpRequest,String body) {
        if (mHttpRequest.uri() == null) {
            return;
        }
        try {
            QueryStringDecoder decoderQuery = new QueryStringDecoder(mHttpRequest.uri());
            Map<String, List<String>> mUriQuery = decoderQuery.parameters();
            String mUri = decoderQuery.path();
            log.info(ctx.channel().remoteAddress().toString() + "  " + mHttpRequest.method().name() + "  " + mHttpRequest.uri());
            ServerJsonEntity mServerJsonEntity = new ServerJsonEntity();
            if (mUri.startsWith(GameServer.ROOTPATH)) {
                GameServer.doGame_STZB(ctx, mHttpRequest, body, mUri, mUriQuery, mServerJsonEntity);
            } else {
                sendResponse(ctx, new ServerJsonEntity().setCode(ServerJsonEntity.Fail).setMsg("Error 404,The path to be lost，请求地址没有匹配到，请检查uri！ 枫林开小差了！"));
            }

        } catch (Exception mE) {
            mE.printStackTrace();
            try {
                sendResponse(ctx, new ServerJsonEntity().setCode(ServerJsonEntity.Fail).setMsg("Error 404,The path to be lost，请求地址没有匹配到，请检查uri！ 枫林开小差了！"));
            } catch (UnsupportedEncodingException mE1) {
                mE1.printStackTrace();
            }
        }

    }


    /**
     * 检查发布者是否具有发布推送权限
     */
    private static boolean checkPermission() {
        //todo 判断是否有权限执行操作
        return true;
    }

    /**
     * 添加通用json数据头,允许跨域
     *
     * @param mResponse
     */
    private static void addResponseHeaders(FullHttpResponse mResponse, boolean contentIsJson) {
        mResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, mResponse.content().readableBytes());
        mResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, contentIsJson ? "application/json" : "text/html;charset=utf-8");
        mResponse.headers().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

    }

    /**
     * 返回结果
     */
    public static void sendResponse(ChannelHandlerContext mCtx, Object mEntity) throws UnsupportedEncodingException {
        FullHttpResponse mResponse = null;
        if (mEntity instanceof ServerJsonEntity) {
            mResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(((ServerJsonEntity) mEntity).toBytes()));
            addResponseHeaders(mResponse, true);
        }
        if (mEntity instanceof String) {
            mResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(((String) mEntity).getBytes("UTF-8")));
            addResponseHeaders(mResponse, false);
        }
        mCtx.writeAndFlush(mResponse);
    }
}

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
    private static Logger log = LogU.getLogU("HttpServerManager", Level.ALL);

    /**
     * 业务处理逻辑第一个方法，分发业务处理逻辑
     */
    public static void onReceiveHandleFirst(ChannelHandlerContext ctx, HttpRequest mHttpRequest, HttpContent mHttpContent) {
        if (mHttpRequest.uri() == null) {
            return;
        }

        try {
            QueryStringDecoder decoderQuery = new QueryStringDecoder(mHttpRequest.uri());
            Map<String, List<String>> mUriQuery = decoderQuery.parameters();
            String mUri = decoderQuery.path();
            log.info(ctx.channel().remoteAddress().toString() + "  " + mHttpRequest.method().name() + "  " + mHttpRequest.uri());
            ServerJsonEntity mServerJsonEntity = new ServerJsonEntity();
            if (mUri.startsWith("/GameSTZB")) {
                doPush(ctx, mHttpRequest, mHttpContent, mUri, mUriQuery, mServerJsonEntity);
            }  else {
            }

        } catch (Exception mE) {
            mE.printStackTrace();
        }

    }

    /**
     * 处理推送业务
     *
     * @param ctx
     * @param mHttpRequest
     * @param mHttpContent
     * @param mUri
     * @param mUriQuery
     * @param mServerJsonEntity
     * @throws UnsupportedEncodingException
     */
    private static void doPush(ChannelHandlerContext ctx, HttpRequest mHttpRequest, HttpContent mHttpContent, String mUri, Map<String, List<String>> mUriQuery, ServerJsonEntity mServerJsonEntity) throws UnsupportedEncodingException {
        switch (mUri) {
//            case "/push/getPushMsgById"://获取推送消息详情
//                mServerJsonEntity = PushManager.push_getPushMsgById(ctx, mUriQuery.get("pushID"));
//                break;
//            case "/push/getPushMsgList"://获取推送消息列表
//                mServerJsonEntity = PushManager.push_getPushMsgList(ctx, mUriQuery.get("index"), mUriQuery.get("size"));
//                break;
//            case "/push/publishPushMsg"://发布推送消息
//                mServerJsonEntity = PushManager.push_publishPushMsg(ctx, mHttpRequest, mHttpContent);
//                break;
//            case "/push/sendPushMsg"://向设备推送消息
//                mServerJsonEntity = PushManager.push_sendPushMsg(ctx, mUriQuery.get("pushID"));
//                break;
        }
        sendResponse(ctx, mServerJsonEntity);
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
        mResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, contentIsJson ? "application/json" : "text/plain");
        mResponse.headers().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

    }

    /**
     * 返回结果
     */
    private static void sendResponse(ChannelHandlerContext mCtx, Object mEntity) throws UnsupportedEncodingException {
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

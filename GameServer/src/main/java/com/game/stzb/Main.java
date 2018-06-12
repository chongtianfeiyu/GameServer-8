package com.game.stzb;

import com.itgowo.SimpleServerCore.Http.HttpServerHandler;
import com.itgowo.SimpleServerCore.Http.HttpServerManager;
import com.itgowo.SimpleServerCore.Utils.LogU;
import com.itgowo.http.ServerJsonEntity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static HttpServerManager mHttpServer = new HttpServerManager();
    private static Logger log = LogU.getLogU("com.itgowo.http.HttpServerManager", Level.ALL);

    public static void main(String[] args) {
        initServer();
    }

    private static void initServer() {
        mHttpServer.setOnReceiveHandleListener(new HttpServerHandler.onReceiveHandlerListener() {
            @Override
            public void onReceiveHandler(HttpServerHandler httpServerHandler) {
                try {
                    log.info(httpServerHandler.getCtx().channel().remoteAddress().toString() + "  " + httpServerHandler.getHttpRequest().method().name() + "  " + httpServerHandler.getUri());
                    log.info(httpServerHandler.getBody(Charset.forName("utf-8")));
                    ServerJsonEntity serverJsonEntity = new ServerJsonEntity();
                    if (httpServerHandler.getUri().startsWith(GameServer.ROOTPATH)) {
                        GameServer.doGame_STZB(httpServerHandler, serverJsonEntity);
                    } else {
                        httpServerHandler.sendData(new ServerJsonEntity().setCode(ServerJsonEntity.Fail).setMsg("Error 404,The path to be lost，请求地址没有匹配到，请检查uri！ 枫林开小差了！"), true);
                    }

                } catch (Exception mE) {
                    mE.printStackTrace();
                    try {
                        httpServerHandler.sendData(new ServerJsonEntity().setCode(ServerJsonEntity.Fail).setMsg("Error 404,The path to be lost，请求地址没有匹配到，请检查uri！ 枫林开小差了！"), true);
                    } catch (UnsupportedEncodingException mE1) {
                        mE1.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        Thread mGameThread = new Thread(() -> {
            try {
                Thread.currentThread().setName("GameMainThread");
                mHttpServer.start(1666);
            } catch (Exception mEm) {
                mEm.printStackTrace();
            }
        });
        mGameThread.start();
    }
}

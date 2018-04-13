package com.itgowo.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.util.logging.Level;
import java.util.logging.Logger;


public class HnHttpServer {
    private EventLoopGroup mBossGroup = new NioEventLoopGroup();
    private EventLoopGroup mWorkerGroup = new NioEventLoopGroup();
    private ServerBootstrap mServerBootstrapm = new ServerBootstrap();
    private Logger log = LogU.getLogU(getClass().getName(), Level.ALL);


    public void start(int port) throws Exception {
        try {
            log.info("The HttpServer is Starting....");
            mServerBootstrapm.group(mBossGroup, mWorkerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpRequestDecoder());
                            ch.pipeline().addLast(new HttpResponseEncoder());
                            ch.pipeline().addLast(new HttpServerInboundHandlerAdapter());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 4096)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = mServerBootstrapm.bind(port).sync();
            log.info("The HttpServer is Started....");
            f.channel().closeFuture().sync();
        } finally {
            mWorkerGroup.shutdownGracefully();
            mBossGroup.shutdownGracefully();
        }
    }

    public void stop() {
        System.out.println("The HttpServer is Stopping");
        mWorkerGroup.shutdownGracefully();
        mBossGroup.shutdownGracefully();
    }


}

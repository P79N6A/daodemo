package com.djs.daodemo.nettyBoot;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class NettyClient {
        private static class SingletonHolder {
            static final NettyClient instance = new NettyClient();
        }
        public static NettyClient getInstance(){
            return SingletonHolder.instance;
        }
        private EventLoopGroup group;
        private Bootstrap b;
        private ChannelFuture cf ;
        private NettyClient(){
            group = new NioEventLoopGroup();
            b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new ReadTimeoutHandler(5));
                            sc.pipeline().addLast(new ClientHandlerOne());  //客户端业务处理类
                        }
                    });
        }
        public void connect(){
            try {
                this.cf = b.connect("127.0.0.1", 8765).sync();
                System.out.println("远程服务器已经连接, 可以进行数据交换..");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public ChannelFuture getChannelFuture(){
            //如果管道没有被开启或者被关闭了，那么重连
            if(this.cf == null){
                this.connect();
            }
            if(!this.cf.channel().isActive()){
                this.connect();
            }
            return this.cf;
        }
}
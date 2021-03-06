package com.djs.daodemo.nettyBoot;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class NettyServer implements Runnable{
        @Override
        public void run() {
            EventLoopGroup pGroup = new NioEventLoopGroup(); //线程组：用来处理网络事件处理（接受客户端连接）
            EventLoopGroup cGroup = new NioEventLoopGroup(); //线程组：用来进行网络通讯读写
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(pGroup, cGroup)
                        .channel(NioServerSocketChannel.class) //注册服务端channel
                        .option(ChannelOption.SO_BACKLOG, 1024)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            protected void initChannel(SocketChannel sc) throws Exception {
                                //5s没有交互，就会关闭channel
                                sc.pipeline().addLast(new ServerHandlerOne());   //服务端业务处理类
                            }
                        });
                ChannelFuture cf = null;
                cf = b.bind(8765).sync();
                cf.channel().closeFuture().sync();
            }
            catch(Exception e)
            {
                pGroup.shutdownGracefully();
                cGroup.shutdownGracefully();
                e.printStackTrace();
            }
        }
    }
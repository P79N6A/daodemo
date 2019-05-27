package com.djs.daodemo.nettyBoot;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.util.ReferenceCountUtil;

public class ClientHandlerOne extends SimpleChannelInboundHandler<HttpObject> {
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
        }
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                ByteBuf buf = (ByteBuf) msg;
                byte[] req = new byte[buf.readableBytes()];
                buf.readBytes(req);
                String body = new String(req, "utf-8");
                System.out.println("Client 接收到server :" + body );
            } finally {
                // 记得释放xxxHandler里面的方法的msg参数: 写(write)数据, msg引用将被自动释放不用手动处理; 但只读数据时,!必须手动释放引用数
                ReferenceCountUtil.release(msg);
            }
        }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client: 读完了");
            ctx.flush();
        }
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
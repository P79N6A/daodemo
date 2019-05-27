package com.djs.daodemo.nettyBoot;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controllerone")
public class TestBoot {

    public void runNettyClient()
    {
        try{
            final NettyClient c = NettyClient.getInstance();
            ChannelFuture cf = c.getChannelFuture();
            cf.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
            cf.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
            cf.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
            cf.channel().closeFuture().sync();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @PostMapping("/login")
    public Object login() {
        try {
            JSONObject ret = new JSONObject();
            runNettyClient();
            ret.put("hh","123456");
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}
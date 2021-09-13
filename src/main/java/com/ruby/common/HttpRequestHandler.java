package com.ruby.common;

import com.ruby.cafeshop.Cafe;
import com.ruby.cafeshop.Cappuccino;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Map;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, FullHttpRequest req) throws Exception {
        Map<String, Object> params = RequestParser.requestParamParser(req);
        Cappuccino cappuccino = new Cappuccino();
        Cafe cafe = cappuccino.gotCappuccino(req.uri().split("/?")[0]);
        cafe.getMethod().invoke(cafe.getClazz(), params);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

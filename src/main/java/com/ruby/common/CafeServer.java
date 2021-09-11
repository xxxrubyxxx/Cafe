package com.ruby.common;

import com.ruby.constents.CafeConstents;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Slf4j
public class CafeServer {

    @SneakyThrows
    public static void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(boss, worker);

        String host = InetAddress.getLocalHost().getHostAddress();
        bootstrap.bind(new InetSocketAddress(host, CafeConstents.PORT)).sync();
        log.info("Cafe server start up on port: {}", CafeConstents.PORT);
    }
}

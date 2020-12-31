package com.wulin.lambdastream.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description: 服务器启动类，实现客户端发送一个请求，服务器会返回 hello netty
 */
public class HelloServer {

    public static void main(String[] args) throws Exception {

        //定义两个线程池(线程组)
        //主线程组，用于接受客户端的连接，不做任何处理
        EventLoopGroup bossGroup =new NioEventLoopGroup() ;
        //从线程组，接受主线程组抛出的任务
        EventLoopGroup wokerGroup =new NioEventLoopGroup() ;

        try {
            //netty服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,wokerGroup)              //设置主从线程组
                    .channel(NioServerSocketChannel.class)   //设置nio双向通道
                    .childHandler(new HelloServerInitializer());                     //子处理器，用于处理wokerGroup

            //用于启动server,并且设置端口，设置启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

            //监听关闭channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }
}
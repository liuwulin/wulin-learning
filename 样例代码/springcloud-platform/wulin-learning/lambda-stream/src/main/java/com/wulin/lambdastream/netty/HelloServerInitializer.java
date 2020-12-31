package com.wulin.lambdastream.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description: 这是一个初始化器，当channle注册后，会执行里面相应的初始化方法
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //通过channel获取对应的管道pipeline
        ChannelPipeline pipeline = channel.pipeline();

        //通过管道pipeline添加handler
        pipeline.addLast("HttpServerCodec",new HttpServerCodec()) //HttpServerCodec是Netty自己提供的助手类，请求到服务端时解码，响应到客户端时编码
                .addLast("customHandler",new CustomHandler())   //添加自定义的助手类 功能待添加
        ;
    }
}
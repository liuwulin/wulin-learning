package com.wulin.netty.core.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author WuLin
 * @Date 2023/1/11 11:08
 * @Desc 业务处理类
 **/
public class MultiplexerTimeServer implements Runnable {
    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;//volatile关键字保证stop字段在多线程的一致性

    /**
     * 初始化多路复用器，绑定监听端口
     *
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();//创建一个selector监听器
            //创建一个Channel通道
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);//支持非阻塞
            //指定socket连接绑定的端口(1024为套接字上请求的最大挂起连接数)
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            //将该Channel通道注册到selector监听器上，注册事件为“OP_ACCEPT”接收请求事件
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The Time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        //停止服务端，将停止参数置为true
        this.stop = true;
    }

    /**
     * 线程执行方法
     */
    @Override
    public void run() {
        while (!stop) {//停止参数不为true的情况下，服务一直启动
            try {
                selector.select(1000);//执行监听，设置超时时间为1000毫秒
                /*获取已经注册的Channel通道上哪些有消息。每一个Channel注册后，都有分配一个
                     独一无二的key，selector可以获取这些活跃的Channel的key，进行遍历*/
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {//遍历活跃的Channel的key
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);//处理该key的请求信息
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();//出现异常后，将该key撤销
                            if (key.channel() != null) {
                                //出现异常后，将该key绑定的通道关闭
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {//判断Key是否还有用
            //处理新接入的请求信息
            if (key.isAcceptable()) {//判断key是否是可接收的
                //接收新的连接
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);//非阻塞
                //添加新的连接至selector监听器
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {//判断key是否是可读取的
                //读取请求数据
                SocketChannel sc = (SocketChannel) key.channel();//获取channel通道
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);//分配一个缓存(与操作系统交互的)
                int readBytes = sc.read(readBuffer);//从缓存中通过通道读取到buffer中
                if (readBytes > 0) {//如果接受的信息不为空
                    readBuffer.flip();//识别是不是一个完整的包
                    byte[] bytes = new byte[readBuffer.remaining()];//创建一个存储信息的byte数组
                    readBuffer.get(bytes);//将buffer中的数据读到byte数组中
                    String body = new String(bytes, "UTF-8");//将byte数组转换为String(并转码)
                    System.out.println("The Time server receive order:" + body);
                    //返回当前的时间给发送方，如果对方发送的请求信息内容为“QUERY TIME ORDER”，则
                    //返回当前时间，如果请求内容不是“QUERY TIME ORDER”，则返回“BAD ORDER”
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                            new java.util.Date(System.currentTimeMillis()).toString()
                            : "BAD ORDER";
                    doWrite(sc, currentTime);//返回消息
                } else if (readBytes < 0) {
                    //对链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    //读到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();//将response字符串序列化
            //创建一个bytes长度的数据缓存
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);//将bytes放入缓存
            writeBuffer.flip();
            channel.write(writeBuffer);//写入通道，反馈给发送端
        }
    }
}

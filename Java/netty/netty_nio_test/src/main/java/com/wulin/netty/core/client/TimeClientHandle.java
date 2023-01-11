package com.wulin.netty.core.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author WuLin
 * @Date 2023/1/11 11:08
 * @Desc 业务处理类
 **/
public class TimeClientHandle implements Runnable{
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;//volatile关键字保证stop字段在多线程的一致性

    public TimeClientHandle(String string, int port) {
        this.host = host == null? "127.0.0.1":host;//指定服务端的主机ip
        this.port = port;//指定服务端的主机端口
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(!stop){
            try {
                selector.select(1000);//向服务端发送请求
                //获取活跃的Channel通道的key，进行遍历
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while(it.hasNext()){//遍历活跃的Channel的key
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);//处理key
                    } catch (Exception e) {
                        if(key != null){
                            key.cancel();//出现异常后，将该key撤销
                            if(key.channel()!=null){
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
        if(key.isValid()){//判断Key是否还有用
            //判断是否连接成功
            SocketChannel sc = (SocketChannel) key.channel();
            if(key.isConnectable()){//判断key是否是可连接的
                if(sc.finishConnect()){//判断通道是否完成连接（三次握手）
                    sc.register(selector, SelectionKey.OP_READ);//注册一个读请求的通道
                    doWrite(sc);
                }else{
                    System.exit(1);//连接失败，进程退出
                }
            }

            if(key.isReadable()){//判断key是否是可读取的(服务端返回的数据)
                //读取请求数据
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);//分配一个缓存(与操作系统交互的)
                int readBytes = sc.read(readBuffer);//从缓存中通过通道读取到buffer中
                if(readBytes>0){//如果接受的信息不为空
                    readBuffer.flip();//识别是不是一个完整的包
                    byte[] bytes = new byte[readBuffer.remaining()];//创建一个存储信息的byte数组
                    readBuffer.get(bytes);//将buffer中的数据读到byte数组中
                    String body = new String(bytes,"UTF-8");//将byte数组转换为String(并转码)
                    System.out.println("Now is:"+body);//打印服务端反馈的信息
                    this.stop = true;
                }else if(readBytes<0){
                    //对链路关闭
                    key.cancel();
                    sc.close();
                }else{
                    //读到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        //创建一个bytes长度的数据缓存
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);//将请求信息的bytes数组放入缓存
        writeBuffer.flip();
        channel.write(writeBuffer);//写入通道，发送给服务端
        if(!writeBuffer.hasRemaining()){
            System.out.println("Send order 2 server succeed.");
        }
    }

    private void doConnect() throws IOException{
        //如果直接连接成功，则注册到多路复用器上，发送请求信息，读取应答
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else{
            //如果连接不成功，则注册一个请求连接类型的通道
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }
}

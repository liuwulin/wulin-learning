package com.wulin.netty.core.server;

/**
 * @Author WuLin
 * @Date 2023/1/11 11:08
 * @Desc 服务端的启动类
 **/
public class TimeServer {
    /**
     * @param args
     * @author Administrator
     * */
    public static void main(String[] args) {
        int port = 8080;//服务端启动端口
        if(args!=null && args.length>0){
            //如果主函数的args参数不为空的话，则取参数中的数据作为启动端口
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                //如果数据获取异常，就采用之前的默认值8080
            }
        }
        //创建服务端启动实例对象(一个实现Runnable接口的线程执行类)
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        //启动Runnable线程执行类“MultiplexerTimeServer”
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}

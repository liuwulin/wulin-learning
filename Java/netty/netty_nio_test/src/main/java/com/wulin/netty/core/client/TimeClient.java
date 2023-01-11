package com.wulin.netty.core.client;

/**
 * @Author WuLin
 * @Date 2023/1/11 11:08
 * @Desc 客户端的启动类
 **/
public class TimeClient {
    /**
     * @param args
     * @author Administrator
     */
    public static void main(String[] args) {
        int port = 8080;//指定需要交互的服务端的端口
        if (args != null && args.length > 0) {
            //如果主函数的args参数不为空的话，则取参数中的数据作为交互端口
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                //如果数据获取异常，就采用之前的默认值8080
            }
        }
        //创建客户端启动实例对象，启动Runnable线程
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}

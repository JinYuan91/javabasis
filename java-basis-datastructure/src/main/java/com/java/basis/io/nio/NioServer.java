package com.java.basis.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.java.basis.io.common.*;
import org.springframework.util.StringUtils;

public class NioServer {

    private  ServerSocketChannel serverSocketChannel;

    private  Selector selector;

    private volatile boolean stop;


    public NioServer(int port){

        try {
            //创建多路复用器selector，工厂方法
            selector = Selector.open();
            //创建ServerSocketChannel，工厂方法
            serverSocketChannel = ServerSocketChannel.open();
            //绑定ip和端口号，默认的IP=127.0.0.1，对连接的请求最大队列长度设置为backlog=1024，如果队列满时收到连接请求，则拒绝连接
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            //设置非阻塞方式
            serverSocketChannel.configureBlocking(false);
            //注册serverSocketChannel到selector多路服用器上面，监听accrpt请求
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("the time is start port = " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }

    public void listen() {
        //如果server没有停止
        while(!stop){
            try {
                //selector.select()会一直阻塞到有一个通道在你注册的事件上就绪了
                //selector.select(1000)会阻塞到1s后然后接着执行，相当于1s轮询检查
                selector.select(1000);
                //找到所有准备接续的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while(it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        //处理准备就绪的key
                        handle(key);
                    }catch (Exception e){
                        if(key != null){
                            //请求取消此键的通道到其选择器的注册
                            key.cancel();
                            //关闭这个通道
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handle(SelectionKey key) throws IOException {
        //如果key是有效的
        if(key.isValid()){
            //监听到有新客户端的接入请求
            //完成TCP的三次握手，建立物理链路层
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = (SocketChannel) ssc.accept();
                //设置客户端链路为非阻塞模式
                sc.configureBlocking(false);
                //将新接入的客户端注册到多路复用器Selector上
                sc.register(selector, SelectionKey.OP_READ);
            }
            //监听到客户端的读请求
            if(key.isReadable()){
                //获得通道对象
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //从channel读数据到缓冲区
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0){
                    //Flips this buffer.  The limit is set to the current position and then
                    // the position is set to zero，就是表示要从起始位置开始读取数据
                    readBuffer.flip();
                    //eturns the number of elements between the current position and the  limit.
                    // 要读取的字节长度
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //将缓冲区的数据读到bytes数组
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("the time server receive order: " + body);
                    String currenttime = "query time order".equals(body) ? new Date(System.currentTimeMillis()).toString(): "bad order";
                    doWrite(sc, currenttime);
                }else if(readBytes < 0){
                    key.channel();
                    sc.close();
                }
            }
        }
    }

    public static void doWrite(SocketChannel channel, String response) throws IOException {
        if(!StringUtils.isEmpty(response)){
            byte []  bytes = response.getBytes();
            //分配一个bytes的length长度的ByteBuffer
            ByteBuffer  write = ByteBuffer.allocate(bytes.length);
            //将返回数据写入缓冲区
            write.put(bytes);
            write.flip();
            //将缓冲数据写入渠道，返回给客户端
            channel.write(write);
        }
    }

    public static void main(String[] args)
    {
        NioServer nioServer=new NioServer(ServerInfo.PORT);
        nioServer.listen();
    }


}

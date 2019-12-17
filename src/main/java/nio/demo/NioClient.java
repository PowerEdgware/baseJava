package nio.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
 
/**
 * Created by zhangyiwen on 16/11/8.
 * 手动输入客户端
 */
public class NioClient {
 
    private InetAddress hostAddress;
    private int port;
 
    private Selector selector;
    private SocketChannel socketChannel;
 
    private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
    static final Charset charset = Charset.forName("UTF-8");
 
    public NioClient(InetAddress hostAddress, int port) throws IOException {
        this.hostAddress = hostAddress;
        this.port = port;//nio.demo.NioClient
        initSelector();
    }
 
    public static void main(String[] args) {
        try {
            new NioClient(InetAddress.getByName("localhost"), 9090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private void initSelector() throws IOException {
        // 创建一个selector
        selector = SelectorProvider.provider().openSelector();
        // 打开SocketChannel
        socketChannel = SocketChannel.open();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 连接指定IP和端口的地址
        socketChannel.connect(new InetSocketAddress(this.hostAddress, this.port));
        // 用selector注册套接字，并返回对应的SelectionKey，同时设置Key的interest set为监听服务端已建立连接的事件
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        // 判断连接是否建立成功，不成功会抛异常
        socketChannel.finishConnect();
        // 开启新线程执行
        new Thread(new ClientThread()).start();
 
        //在主线程中 从键盘读取数据输入到服务器端
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            if("".equals(line)) continue; //不允许发空消息
            socketChannel.write(charset.encode(line));//sc既能写也能读，这边是写
        }
        scan.close();
    }
 
    private class ClientThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                	read();
                	System.out.println("xxxx"+LocalDateTime.now());
                   // selector.select();
                } catch (Exception e) {
                    e.printStackTrace();
                }
 
                Iterator<?> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = (SelectionKey) selectedKeys.next();
                    selectedKeys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    dispatch(key);
                }
            }
        }
 
        /**
         * 事件处理分发
         * @param key 已经ready的selectionKey
         */
        private void dispatch(SelectionKey key){
            try {
                if (key.isConnectable()) {
                    System.out.println("[event]connect.");
                    finishConnection(key);
                } else if (key.isReadable()) {
                    System.out.println("[event]read");
                    read(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
                key.channel();
                try {
                    if(key.channel()!=null){
                        key.channel().close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
 
    /**
     * 完成与服务端连接
     * @param key
     * @throws IOException
     */
    private void finishConnection(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 判断连接是否建立成功，不成功会抛异常
        socketChannel.finishConnect();
        // 设置Key的interest set为OP_WRITE事件
        key.interestOps(SelectionKey.OP_READ);
    }
 
    /**
     * 处理read
     * @param key
     * @throws IOException
     */
    private void read(SelectionKey key) throws IOException {
        // 读取数据
        SocketChannel socketChannel = (SocketChannel) key.channel();
        readBuffer.clear();
        StringBuilder content = new StringBuilder();
        int readNum = socketChannel.read(readBuffer);
        if(readNum==0){
            return;
        }else if(readNum<0){
            throw new IOException("exception.");
        }else {
            readBuffer.flip();
            content.append(charset.decode(readBuffer)); //decode
        }
        while(socketChannel.read(readBuffer) > 0)
        {
            readBuffer.flip();
            content.append(charset.decode(readBuffer));
        }
        // 处理数据
        process(content.toString(), key);
        // 设置Key的interest set为OP_READ事件
//        key.interestOps(SelectionKey.OP_READ);
    }
 
    /**
     * 处理服务端响应数据
     * @param content
     */
    private void process(String content,SelectionKey key) {
        System.out.println("[Client receive from server] -> content: " + content);
    }
    
    /**
     * 处理read
     * @param key
     * @throws IOException
     */
    private void read() throws IOException {
        // 读取数据
        readBuffer.clear();
        StringBuilder content = new StringBuilder();
        int readNum = socketChannel.read(readBuffer);
        if(readNum==0){
            return;
        }else if(readNum<0){
            throw new IOException("exception.");
        }else {
            readBuffer.flip();
            content.append(charset.decode(readBuffer)); //decode
        }
        while(socketChannel.read(readBuffer) > 0)
        {
            readBuffer.flip();
            content.append(charset.decode(readBuffer));
        }
        // 处理数据
        process(content.toString(), null);
        // 设置Key的interest set为OP_READ事件
//        key.interestOps(SelectionKey.OP_READ);
    }
 
 
}

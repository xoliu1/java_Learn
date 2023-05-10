package 网络编程.socker01;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class cilent01 {
    public static void main(String[] args) throws IOException {
        //1.生成一个Socket对象，连接服务端的接口
        //new Socket(ip地址,端口)
        //下行代码为链接本机的9999端口
        Socket socket = new Socket(InetAddress.getLocalHost(),9998);
        //2.连接上后，生成Socket
        // 通过socket.getOutStream()的输出流写入数据到数据通道
        //生成和该通道绑定的输出流对象
        OutputStream socketOutputStream = socket.getOutputStream();
        socketOutputStream.write("hello,world".getBytes());
        //一定记得关闭socket和输出流对象
        socketOutputStream.close();
        socket.close();;
    }
}

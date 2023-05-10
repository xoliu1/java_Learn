package 网络编程.socker01;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server01 {
    public static void main(String[] args) throws IOException {
        //1.建立ServerSocket端口
        //细节：要求本机的该端口没有被其他服务监听
        ServerSocket serverSocket = new ServerSocket(9998);
        //2.当没有客户端连接该端口时，程序会堵塞，等待连接
        //如果有客户端连接，就会返回一个Socket对象，程序继续
        Socket socket = serverSocket.accept();
        //3.通过getInputStream()得到输入流对象
        InputStream socketInputStream = socket.getInputStream();
        int readlen = 0;
        byte[] buf = new byte[1024];
        while((readlen = socketInputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,readlen));
        }
        //关闭
        socketInputStream.close();
        socket.close();
    }
}

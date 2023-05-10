package 网络编程.socker01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class cilent {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        OutputStream out = socket.getOutputStream();

        FileInputStream in = new FileInputStream("D:\\pic.jpg");
        byte[] chars = new byte[1024];
        int readlen = 0;
        while((readlen = in.read(chars)) != -1){
            out.write(chars,0,readlen);
        }
     //   socket.shutdownOutput();
        OutputStream socketOutputStream = socket.getOutputStream();
        socketOutputStream.write("hello,world".getBytes());
        //一定记得关闭socket和输出流对象
        in.close();
        socket.close();;
        out.close();
    }
}

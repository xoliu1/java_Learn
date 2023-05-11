package 网络编程.socker01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();

        InputStream socketInputStream = socket.getInputStream();

        FileOutputStream out = new FileOutputStream("A:\\xoliu1111.jpg");


        int readlen = 0;
        byte[] buf = new byte[1024];
        while((readlen = socketInputStream.read(buf)) != -1){
            out.write(buf,0,readlen);
        }
        //关闭
        socketInputStream.close();
        out.close();
        socket.close();
    }
}

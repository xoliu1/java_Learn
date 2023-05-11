package 网络编程.上传视频给另一个用户;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/


public class recevier01 {
    public static void main(String[] args) throws IOException {
        String download_Path = "D:\\data.jpg";
        FileOutputStream fileOutputStream = new FileOutputStream(download_Path);
        ServerSocket serverSocket = new ServerSocket(9837);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readlen = 0;
        while ((readlen = in.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, readlen);
        }
        socket.close();
        serverSocket.close();
        fileOutputStream.close();
        in.close();

    }
}

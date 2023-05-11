package 网络编程.上传视频给另一个用户;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/


import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class sender01 {
    public static void main(String[] args) throws IOException {
        String path = "A:\\tmp.mp4";
        FileInputStream fileInputStream = new FileInputStream(path);
        Socket socket = new Socket(InetAddress.getByName("192.168.1.74"), 9837);
        OutputStream out = socket.getOutputStream();
        int readlen = 0;
        byte[] bytes = new byte[1024];
        while ((readlen = fileInputStream.read(bytes)) != -1){
            out.write(bytes, 0, readlen);
        }
        fileInputStream.close();
        out.close();
        socket.close();
    }
}
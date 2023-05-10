package 网络编程.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class receiver {
    /*
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6666);

        //创建接收对象
        byte[] buf = new byte[1024];
        //一个数据包最大64k
        DatagramPacket receiver = new DatagramPacket(buf, buf.length);
        //调用接收方法,将通过网络传输的Packet对象填充到我这里的receiver
        socket.receive(receiver);
        //一旦有数据包传输到该socket对应的端口，即6666时，就会生效，否则处于堵塞等待状态
        //数据将会传入packet对象receiver中
        int length = receiver.getLength();//显示实际收到的数据字节长度
        byte[] data = receiver.getData();//返回接受的数据
        System.out.println(new String(data,0,length));
        //记得关闭
        socket.close();
        System.out.println("接收端已完成");
    }*/

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6666);
        byte[] buf = new byte[1024];
        DatagramPacket receiver = new DatagramPacket(buf, buf.length);
        socket.receive(receiver);
        int length = receiver.getLength();
        byte[] data = receiver.getData();
        System.out.println(new String(data,0,length));
        socket.close();
    }
}

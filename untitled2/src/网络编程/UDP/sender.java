package 网络编程.UDP;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class sender {
    public static void main(String[] args) throws IOException {
        //1.先创建DatagramSocket对象准备发送
        DatagramSocket socket = new DatagramSocket(8965);
        //这里的端口最好不要和接收端的端口一样

        //2.将数据封装到packet对象中
        //发送的packet对象需要写入主机IP和端口
        //或者getLocalHost()
        DatagramPacket sender = new DatagramPacket("傻逼樊光辉".getBytes(), "傻逼樊光辉".getBytes().length,/* InetAddress.getByName("192.168.1.74")*/InetAddress.getLocalHost(),6666);
        socket.send(sender);

        //关闭资源
        socket.close();
        System.out.println("发送端已完成");
    }
}

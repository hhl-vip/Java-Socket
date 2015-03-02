package server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static void main(String[] args) throws Exception{
		byte buf [] = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		DatagramSocket ds = new DatagramSocket(5678);
		
		while (true) {
			ds.receive(dp);//接收数据包
			buf = dp.getData();//获取数据包中数据
			ByteArrayInputStream bais = new ByteArrayInputStream(buf);//将字节数组转为字节数组输入流
			DataInputStream dis = new DataInputStream(bais);
			long num = dis.readLong();//从包含的输入流中读取此操作需要的字节。
			System.out.println(num);
		}
	}
}

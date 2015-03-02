package client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPClient {
	public static void main(String[] args) throws Exception{
		
		long num = 10000l;//声明long类型变量num，并赋值
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//创建一个字节数组输出流管道
		DataOutputStream dos = new DataOutputStream(baos);//创建一个二进制数字输出管道
		dos.writeLong(num);//将一个 long 值以 8-byte 值形式写入基础输出流中，先写入高字节。
		byte [] buf = baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 5678));
		DatagramSocket ds = new DatagramSocket(9999);
		ds.send(dp);
		ds.close();
	}
	
}

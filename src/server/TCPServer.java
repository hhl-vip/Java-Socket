package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * server 
 * @author Tomcatx
 *
 */
public class TCPServer {
	 final int port=8000;
	 private ServerSocket ss;
	 
	 public TCPServer()throws IOException{
		 ss = new ServerSocket(port);
		 System.out.println("服务器已启动");
	 }
	 
	 public String echo(String msg){
		 return "echo:"+msg;
	 }
	
	 private PrintWriter getWriter(Socket socket){
		 OutputStream os=null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return new PrintWriter(os,true);
	 }
	 
	 private BufferedReader getReader(Socket socket){
		 InputStream is=null;
		try {
			is = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return new BufferedReader(new InputStreamReader(is));
	 }
	 
	 public void service(){
		 while (true) {
			Socket socket=null;
			
				try {
					socket = ss.accept();
					System.out.println("new connection accepted "+socket.getInetAddress()+" "+socket.getPort());
					BufferedReader br = getReader(socket);
					PrintWriter pw = getWriter(socket);
					
					String msg = null;
					while ((msg=br.readLine())!=null) {
						System.out.println(msg);
						pw.println(echo(msg));
						if (msg.equals("bye")) {
							break;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if (socket!=null) {
						try {
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		}
	 }
	 public static void main(String[] args) {
		try {
			new TCPServer().service();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class Client {
	 final String host="localhost";
	 final int port=8000;
	 private Socket socket;
	 
	 public Client()throws IOException{
		 socket = new Socket(host,port);
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
	 
	 public void client(){
		 BufferedReader br = getReader(socket);
		 PrintWriter pw = getWriter(socket);
		 BufferedReader bb = new BufferedReader(new InputStreamReader(System.in));
		 String msg=null;
		 try {
			while ((msg=bb.readLine())!=null) {
					pw.println(msg);
					System.out.println(br.readLine());
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
	 
	 public static void main(String[] args) {
		try {
			new Client().client();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

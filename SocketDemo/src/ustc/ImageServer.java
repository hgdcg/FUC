package ustc;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ImageServer extends Thread{
	
	private Socket socket = null;
	
	private HashSet<String> ips = new HashSet<String>();

	public ImageServer(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {

		try {
			FileInputStream fileInputStream = new FileInputStream("F://test.jpg");
			OutputStream outputStream = socket.getOutputStream();
			
			int length = -1;
			byte[] buf = new byte[1024];
			while((length = fileInputStream.read(buf)) != -1) {
				outputStream.write(buf,0,length);
			}
			
			String ip = socket.getInetAddress().getHostAddress();
			if(ips.add(ip)) {
				System.out.println(String.format("client %s connected. the client count is %s.", ip, ips.size()));
			}
			
			fileInputStream.close();
			outputStream.close();
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws Exception{

		ServerSocket serverSocket = new ServerSocket(9090);
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				new ImageServer(socket).start();
			}
		} catch(Exception e) {
			throw e;
		} finally {
			serverSocket.close();
		}

	}

}

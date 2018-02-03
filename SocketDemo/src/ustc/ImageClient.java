package ustc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ImageClient {

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
		InputStream inputStream = socket.getInputStream();
		FileOutputStream fileOutputStream = new FileOutputStream("F://download.jpg");
		
		int length = -1;
		byte[] buf = new byte[1024];
		while((length = inputStream.read(buf)) != -1) {
			fileOutputStream.write(buf, 0, length);
		}
		fileOutputStream.close();
		inputStream.close();
		socket.close();

	}

}

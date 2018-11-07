package cn.tedu.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 这个类作为线程类，抽取start方法的代码。
 * 
 * @author soft01
 *
 */
public class ClinetHandler implements Runnable {

	// 1.声明Socket对象
	private Socket socket;

	public ClinetHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// 获取请求行的数据。
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = reader.readLine(); // GET / HTTP/1.1
			System.out.println(line);
			if (line != null) {
				String url = line.split(" ")[1];
				if("/".equals(url)) {
					url = "/index.html";
				}

				// 抽取响应代码
				PrintStream ps = new PrintStream(socket.getOutputStream());
				ps.println("HTTP/1.1 200 OK"); // 状态行
				ps.println("Content-Type:text/html"); // 响应文件类型

				// File file = new File("WebContent/index.html");
				File file = new File("WebContent" + url);
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

				ps.println("Content-Length:" + file.length()); // 响应文件的大小

				ps.println(); // 空行

				byte[] b = new byte[(int) file.length()];
				in.read(b);
				ps.write(b); // 发送响应正文

				ps.flush();
				in.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

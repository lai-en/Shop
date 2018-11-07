package cn.tedu.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这个类用来代表服务端的程序。
 * @author soft01
 *
 */
public class WebServer {
	//1.声明一个ServerSocket对象。
	private ServerSocket server;
	//声明线程池对象。	(去了解BIO、NIO和AIO以及它们的的区别)

	private ExecutorService threadPool;

	//2.在构造方法中完成ServerSocket对象的初始化。
	public WebServer() {
		try {
			server = new ServerSocket(8080);
			
			//初始化线程池对象，最大线程数是100。
			threadPool = Executors.newFixedThreadPool(100);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//3.提供start方法，接收客户端的请求并响应
	public void start() {
		try {
			while(true) {
				Socket socket = server.accept();
				threadPool.execute(new ClinetHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//4.提供main方法，启动服务器
	public static void main(String[] args) {
		new WebServer().start();	//接受客户端请求，并响应。
	}
}

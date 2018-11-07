package cn.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 这个类用来提供jdbc的工具类。
 * @author Administrator
 *
 */
public class JDBCUtils {
	private JDBCUtils(){}
	private static ResourceBundle rb;
	
	static{
		/**
		 * 外部配置文件大概可以分为两种属性文件和XML文件。
		 * 属性文件必须放在src目录下才可以。
		 * 使用外部的属性文件在作为配置文件，用ResourceBundle.getBundle(文件名)ResourceBundle对象。
		 * ResourceBundle类的内部维护了一个map散列表，所以用key-value的方式来获取值。
		 */
		rb = ResourceBundle.getBundle("jdbc");
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			/*
			 * getString()方法返回的是键对应的值。
			 */
			//注册驱动在高级的JDK版本中可以省略，但是在低版本的JDK中是不可以省略的。
			Class.forName(rb.getString("driverClass"));
			
			String url = rb.getString("jdbcUrl");
			String user = rb.getString("user");
			String password = rb.getString("password");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static void close(ResultSet rs, Statement st, Connection conn){
		/**
		 * 为了确保关闭。
		 */
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				rs = null;
			}
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				st = null;
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
}
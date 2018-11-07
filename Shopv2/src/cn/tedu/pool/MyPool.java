package cn.tedu.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import cn.tedu.util.JDBCUtils;

//1.实现DataSource接口。
public class MyPool implements DataSource{
	//2.创建容器，用来存放数据库连接对象。(因为总要在连接池中增加或者删除连接，所以用链表的数据结构更加合适)
	static List<Connection> pool = new LinkedList<>();
	
	//3.在静态代码块中完成容器的初始化。
	static{
		for (int i = 0; i < 3; i++) {
			Connection conn = JDBCUtils.getConnection();
			pool.add(conn);
		}
	}
	
	
	//4.提供一个getConnection方法，用来对外界提供一个数据库连接。
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = pool.remove(0);
		System.out.println("连接被拿走了一个，现在还剩"+pool.size()+"个！");
		return conn;
	}
	
	//5.提供一个returnConnection方法。
	public void returnConnection(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
				pool.add(conn);
				System.out.println("连接已经还回，现在还剩"+pool.size()+"几个");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
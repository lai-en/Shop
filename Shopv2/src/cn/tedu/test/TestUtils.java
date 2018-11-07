package cn.tedu.test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.tedu.util.JDBCUtils;

public class TestUtils {
	
	@Test
	public void hello() {
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
//		MyPool pool = new MyPool();
		ComboPooledDataSource pool = new ComboPooledDataSource();
		
		try {
			// 1.注册驱动2.获取数据库连接
//			conn = JDBCUtils.getConnection();
//			pool.setDriverClass("com.mysql.jdbc.Driver");
//			pool.setJdbcUrl("jdbc:mysql:///jtdb");
//			pool.setUser("root");
//			pool.setPassword("");
			//从连接池获取连接。
			conn = pool.getConnection();
			
			
			// 3.获取传输器。
			st = conn.createStatement();

			// 4.执行SQL。
			String sql = "select * from user";
			rs = st.executeQuery(sql);

			// 5.遍历结果集。
			while (rs.next()) {
				// 根据列的索引获取数据，获取第一列的值。
				String id = rs.getString(1);

				// 获取第二列的值。
				String username = rs.getString(2);

				// 获取第三列的值。
				String password = rs.getString(3);
				System.out.println(id + username + password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 6.释放资源。
			JDBCUtils.close(rs, st, null);
//			pool.returnConnection(conn);
		}
	}
}
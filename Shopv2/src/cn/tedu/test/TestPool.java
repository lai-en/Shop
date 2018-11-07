package cn.tedu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.mysql.jdbc.Driver;

import cn.tedu.util.JDBCUtils;

public class TestPool {
	
	@Test
	public void hello() {
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		try {
			// 1.注册驱动2.获取数据库连接
			conn = JDBCUtils.getConnection();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6.释放资源。
			JDBCUtils.close(rs, st, conn);
		}
	}
}
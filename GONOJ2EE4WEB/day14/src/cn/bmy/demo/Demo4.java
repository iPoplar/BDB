package cn.bmy.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class Demo4 
{
	@Test
	public void insert()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "insert into user(id,name,password,email,birthday) values (4,'Be','123','xixi@baidu.com',to_date('1890-09-09','YYYY-MM-DD'))";
			int num = st.executeUpdate(sql);
			if(num>0)
			{
				System.out.println("hehe插入成功Ol！！！");
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	@Test
	public void delete()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			conn = JdbcUtils.getConnection();
			String sql = "delete from users where id=4";
			st = conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
			{
				System.out.println("删除成功！！！");
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, st, rs);
		}
	}

	public void find()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			conn = JdbcUtils.getConnection();
			String sql = "select * form users where id=1";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, st, rs);
		}
		
		
		
	}
}

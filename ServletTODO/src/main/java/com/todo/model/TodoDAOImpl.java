package com.todo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TodoDAOImpl implements TodoDAO{

	private static TodoDAOImpl instance = new TodoDAOImpl();
	
	public static TodoDAOImpl getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds= (DataSource) envCtx.lookup("jdbc/react");
		return ds.getConnection();
	}
	
	@Override
	public void todoInsert(Todo todo) {
		Connection con = null;
		PreparedStatement ps= null;
		
		try {
			con=getConnection();
			String sql="insert into todo(name, regdate, subject, summary) values(?,now(),?,?)";
			ps= con.prepareStatement(sql);
			ps.setString(1, todo.getName());
			ps.setString(2, todo.getSubject());
			ps.setString(3, todo.getSummary());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,null,ps,null);
		}
		
		
	}

	@Override
	public ArrayList<Todo> getList() {
		Connection con= null;
		Statement st= null;
		ResultSet rs= null;
		ArrayList<Todo> arr= new ArrayList<>();
		try {
			con=getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from todo");
			while(rs.next()) {
				Todo todo= new Todo();
				todo.setName(rs.getString("name"));
				todo.setNum(rs.getLong("num"));
				todo.setRegdate(rs.getDate("regdate"));
				todo.setSubject(rs.getString("subject"));
				todo.setSummary(rs.getString("summary"));
				arr.add(todo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,st,null,rs);
		}
		
		return arr;
		
		
	}

	
	private void closeConnection(Connection con, Statement st, PreparedStatement ps, ResultSet rs ) {
		
		try {
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(ps!=null)ps.close();
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.todo.action;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.todo.model.Todo;
import com.todo.model.TodoDAOImpl;

/**
 * Servlet implementation class InsertAction
 */
@WebServlet("/todo/insert")
public class InsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StringBuffer sb= new StringBuffer();
		BufferedReader reader= request.getReader();
		String line = null;
		while((line = reader.readLine())!=null){
			sb.append(line);
		}
		Gson gson = new Gson();
		Todo todo = gson.fromJson(sb.toString(), Todo.class);
		
		TodoDAOImpl dao = TodoDAOImpl.getInstance();
		dao.todoInsert(todo);
		
	}
	
	

}


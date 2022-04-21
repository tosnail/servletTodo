package com.todo.model;

import java.util.ArrayList;

public interface TodoDAO {

	//추가
	public void todoInsert(Todo todo) ;
	
	//전체보기
	public ArrayList<Todo> getList();
	
}

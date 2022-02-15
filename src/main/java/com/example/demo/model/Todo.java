package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todos")

public class Todo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="todo_name")
	private String todoName;
	
	public Todo() {
		
	}
	
	public Todo(String todoName) {
		super();
		this.todoName = todoName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTodoName() {
		return todoName;
	}
	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}
	
	

}

package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;

@RestController
@CrossOrigin(origins ="http://localhost:4200/")
@RequestMapping("/api/v1/")
public class TodoController {
	
	
	
	@Autowired
	private TodoRepository todoRepository;
	
	//Get all todos
	@GetMapping("/todos")
	public List<Todo> getAllTodos(){
		return todoRepository.findAll();
	}
	
	//create todo rest api
	@PostMapping("/todos")
	public Todo createTodo(@RequestBody Todo todo) {
	return todoRepository.save(todo);
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not exist with Id : " + id));
		return ResponseEntity.ok(todo);
	}
	
	@PutMapping("todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails){
		Todo todo = todoRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Todo does not exist" + id));
		
		todo.setTodoName(todoDetails.getTodoName());
		Todo updatedTodo = todoRepository.save(todo);
		return ResponseEntity.ok(updatedTodo);
	}
	
	@DeleteMapping("todos/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTodo(@PathVariable Long id){
		Todo todo = todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo does not exist" + id));
		
		todoRepository.delete(todo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}

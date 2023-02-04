package com.rest.todos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("http://localhost:4200")
@RestController
public class TodoJPAResource {
	
	public TodoHardCodedService service;
	
	@Autowired
	private TodoJPARespository todoJpaRespostory;
	
	@GetMapping("jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		return todoJpaRespostory.findByUsername(username);
		//return service.findAll();
	}
	
	@GetMapping("jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id){
		return todoJpaRespostory.findById(id).get();
		//return service.findById(id);
	}
	
	@DeleteMapping("jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		todoJpaRespostory.deleteById(id);
		return ResponseEntity.noContent().build();

		//return ResponseEntity.notFound().build();
	}
	
	@PutMapping("jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
		Todo todoupdated =todoJpaRespostory.save(todo);

		return new ResponseEntity<Todo>(todo,HttpStatus.OK);
	}
	
	@PostMapping("jpa/users/{username}/todos")
	public ResponseEntity<Void> addTodo(@PathVariable String username, @RequestBody Todo todo) {
		todo.setUsername(username);
		Todo createdTodo =todoJpaRespostory.save(todo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}

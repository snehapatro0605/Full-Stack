package com.rest.todos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoJPARespository extends JpaRepository<Todo,Long> {
	
	List<Todo> findByUsername(String username);

}

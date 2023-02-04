package com.rest.hellocontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.bean.HelloWorldBean;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {
	
	@GetMapping("/hello-world")
	public String getString() {
		return "Hi World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean getBean() {
		//return new HelloWorldBean("Hi World");
		throw new RuntimeException("Some error occured");
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean getPathName(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hi World, %s", name));
	}

}

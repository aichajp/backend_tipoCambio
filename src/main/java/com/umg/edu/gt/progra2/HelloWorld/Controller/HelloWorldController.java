package com.umg.edu.gt.progra2.HelloWorld.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

		@GetMapping("/hello")
		public String Hello() {
			return "Hola mundo!";
		}
	
}

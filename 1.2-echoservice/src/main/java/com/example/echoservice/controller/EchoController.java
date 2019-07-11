package com.example.echoservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

	@Value("${echomessage:Hello World!}")
	private String message;
	
	@GetMapping("/message")
	public String getMessage() {
		return message;
	}
}

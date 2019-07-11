package com.example.dynamicechoservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class EchoController {

	@Value("${echomessage:Hello World!}")
	private String message;
	
	@GetMapping("/message")
	public String getMessage() {
		return message;
	}
}

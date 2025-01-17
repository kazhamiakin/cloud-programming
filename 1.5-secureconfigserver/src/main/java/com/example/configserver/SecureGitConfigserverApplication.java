package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SecureGitConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureGitConfigserverApplication.class, args);
	}

}

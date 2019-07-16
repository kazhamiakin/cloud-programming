package com.example.shoppurchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopPurchaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopPurchaseApplication.class, args);
	}
}
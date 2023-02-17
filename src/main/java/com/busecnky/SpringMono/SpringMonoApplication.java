package com.busecnky.SpringMono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringMonoApplication {

	@GetMapping("/mesaj")
	public String merhaba(){
		return "merhaba mesajı yayınlıyoruz";
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringMonoApplication.class, args);
	}

}

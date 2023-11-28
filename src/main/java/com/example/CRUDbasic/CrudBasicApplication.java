package com.example.CRUDbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing // BaseEntity 관련
public class CrudBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBasicApplication.class, args);
	}

}

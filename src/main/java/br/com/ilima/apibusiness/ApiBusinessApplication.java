package br.com.ilima.apibusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class ApiBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBusinessApplication.class, args);
	}

}
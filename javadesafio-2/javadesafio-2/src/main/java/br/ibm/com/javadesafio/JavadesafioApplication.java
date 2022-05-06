package br.ibm.com.javadesafio;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@EnableFeignClients
public class JavadesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavadesafioApplication.class, args);
	}

}

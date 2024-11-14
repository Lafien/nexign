package com.nefedov.nexigntestapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NexignTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexignTestApplication.class, args);
	}

}

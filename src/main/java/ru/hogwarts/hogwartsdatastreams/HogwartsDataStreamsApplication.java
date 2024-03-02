package ru.hogwarts.hogwartsdatastreams;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition
@SpringBootApplication
public class HogwartsDataStreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HogwartsDataStreamsApplication.class, args);
	}

}

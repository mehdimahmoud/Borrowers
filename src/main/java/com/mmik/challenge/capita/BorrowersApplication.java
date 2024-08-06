package com.mmik.challenge.capita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springdoc.core.models.GroupedOpenApi;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.mmik.challenge.capita"})
public class BorrowersApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(BorrowersApplication.class, args);
	}

	@Bean
	public OpenAPI borrowersApi() {
		return new OpenAPI()
				.info(new Info().title("Borrowers Services API")
						.description("Borrowers Services API")
						.version("2.0")
						.license(new License().name("Apache License Version 2.0")
								.url("https://github.com/mehdi.mahmoud/springboot/LICENSE")));
	}

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder()
				.group("borrowers")
				.pathsToMatch("/**")
				.build();
	}

}

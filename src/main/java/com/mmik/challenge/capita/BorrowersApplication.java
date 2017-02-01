package com.mmik.challenge.capita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.mmik.challenge.capita"})
@EnableSwagger2
public class BorrowersApplication {
	public static void main(String[] args) {
		SpringApplication.run(BorrowersApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("borrowers")
				.apiInfo(apiInfo())
				.enable(true)
				.select()
				.paths(regex("/borrower.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Borrowers Services API")
				.description("Borrowers Services API")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/mehdi.mahmoud/springboot/LICENSE")
				.version("2.0")
				.build();
	}
}

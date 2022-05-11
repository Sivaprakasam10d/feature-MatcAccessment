package com.matc.MATC.AccessMent.Java;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@SecurityScheme(name = "Authentication",description = "JWT token",type = SecuritySchemeType.HTTP,
		scheme = "bearer",bearerFormat = "JWT",in = SecuritySchemeIn.HEADER)

@OpenAPIDefinition(info = @Info(title = "MATC Assessments ",version = "1.0.0",
		description = "MATC system is used for management of Build a Employee REST service with JWT"
				+ " to serve the needs in a efficient manner. "
				+ "API Used for managing employees"))
@EnableMongoRepositories
public class MatcAccessMentJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcAccessMentJavaApplication.class, args);
	}

}

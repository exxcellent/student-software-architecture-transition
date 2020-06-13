package de.exxcellent.student.softwarearchitecture.transition;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories
@EntityScan("de.exxcellent.student.softwarearchitecture.transition")
@OpenAPIDefinition(
		info = @Info(title = "Thesis Server API", version = "1.0", description = "REST-API of the reference project."),
		servers = @Server(url = "http://localhost:9000")
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

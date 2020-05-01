package de.exxcellent.student.softwarearchitecture.transition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories
@EntityScan("de.exxcellent.student.softwarearchitecture.transition.businesslogic.components")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
package de.exxcellent.theses.softwarearchitecture.layerarchitecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories
@EntityScan("de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components")
public class LayerArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(LayerArchitectureApplication.class, args);
	}

}

package dev.pedroabreu.restapi;

import dev.pedroabreu.restapi.run.Location;
import dev.pedroabreu.restapi.run.Run;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Aplicação iniciada na porta 8080");

	}

	// CommmandLineRunner é uma interface funcional que permite executar um bloco de código após a inicialização da aplicaçã
	@Bean
	CommandLineRunner runner() {
		return args -> {
			Run run = new Run(1, "Corrida no parque", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 10, Location.OUTDOOR);
			log.info(run.toString());
		};
	}
}

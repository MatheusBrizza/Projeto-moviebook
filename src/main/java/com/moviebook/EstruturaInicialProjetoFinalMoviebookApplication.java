package com.moviebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EstruturaInicialProjetoFinalMoviebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstruturaInicialProjetoFinalMoviebookApplication.class, args);
	}

}

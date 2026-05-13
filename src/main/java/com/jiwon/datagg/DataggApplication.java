package com.jiwon.datagg;

import com.jiwon.datagg.riot.config.RiotApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RiotApiProperties.class)
@SpringBootApplication
public class DataggApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataggApplication.class, args);
	}

}

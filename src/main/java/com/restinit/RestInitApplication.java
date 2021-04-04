package com.restinit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RestInitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestInitApplication.class, args);
	}

}

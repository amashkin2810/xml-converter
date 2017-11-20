package com.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration 

public class ConverterApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ConverterApplication.class);
	}
}

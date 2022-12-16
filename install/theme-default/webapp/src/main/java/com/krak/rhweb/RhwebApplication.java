package com.krak.rhweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class RhwebApplication implements CommandLineRunner {
	
	@Autowired
    private CustomProperties properties;
	
	public static void main(String[] args) {
		SpringApplication.run(RhwebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("DataSource API URL: "+ properties.getApiUrl());
		
	}

}

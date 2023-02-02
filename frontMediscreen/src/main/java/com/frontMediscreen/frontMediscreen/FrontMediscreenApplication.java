package com.frontMediscreen.frontMediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.frontMediscreen.frontMediscreen")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FrontMediscreenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontMediscreenApplication.class, args);
	}

}

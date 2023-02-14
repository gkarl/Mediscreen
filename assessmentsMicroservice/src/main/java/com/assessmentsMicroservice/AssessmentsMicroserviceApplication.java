package com.assessmentsMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.assessmentsMicroservice")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AssessmentsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentsMicroserviceApplication.class, args);
	}

}

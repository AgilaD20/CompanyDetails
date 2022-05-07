package com.estock.companyinfomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CompanyInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyInfoApplication.class, args);
	}

}

package com.spring.boot.jpa.web;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@SpringBootApplication
public class  Assignment4_Org_Application implements WebMvcConfigurer{
	public static void main(String[] args) {
		SpringApplication.run(Assignment4_Org_Application.class, args);
		System.setProperty("spring.config.name", "organization");
		System.out.println("Spring boot web  for Organization is start here:  http://localhost:8082/");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/aboutus").setViewName("aboutus");
	}
	
	@Bean
	public ApplicationRunner orgsInitializer(OrgRepository orgRepository) {
		return args -> {	
			if(orgRepository.findAll().size() == 0) {
			orgRepository.saveAndFlush(new Organization(1, "TD Bank", "320 Front St W", "N1V 3S8", "4161008888", "hr@td.com", "http://www.td.com"));
			orgRepository.saveAndFlush(new Organization(2, "BMO", "88 King St ", "B1V 5Z9", "4162008888", "hr@bmo.com", "http://www.bmo.com"));
			orgRepository.saveAndFlush(new Organization(3, "RBC Bank", "86 Queen St ", "N2V 3S6", "4163008888", "hr@rbc.com", "http://www.rbc.com"));
			}		
		};
	}
	
	

}

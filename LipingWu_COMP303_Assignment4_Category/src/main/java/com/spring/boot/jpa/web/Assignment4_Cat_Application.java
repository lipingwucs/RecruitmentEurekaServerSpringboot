//Liping Wu 300958061 COMP303_Assignment4-Category 
package com.spring.boot.jpa.web;

import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@SpringBootApplication
public class Assignment4_Cat_Application implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(Assignment4_Cat_Application.class, args);
		System.setProperty("spring.config.name", "category");
		System.out.println("Spring boot web  for category is start here:  http://localhost:8083/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/aboutus").setViewName("aboutus");
	}

	@Bean
	public ApplicationRunner catsInitializer(CatRepository catRepository) {
		return args -> {
			if (catRepository.findAll().size() == 0) {
				catRepository.saveAndFlush(new Category(1, "c0001", "Management", "Management position"));
				catRepository.saveAndFlush(new Category(2, "c0002", "Market", "Marketing, sales person position"));
				catRepository.saveAndFlush(new Category(3, "c0003", "Technical", "professional technical position"));
			}
		};
	}

}

/*COMP303_Assignment4-Job-Web
 *Liping Wu 300958061
 *4-5-2020
 * */

package spring.boot.jpa.web.job;

import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@SpringBootApplication
public class Assignment4_Job_Application implements WebMvcConfigurer   {

	public static void main(String[] args) {
		SpringApplication.run(Assignment4_Job_Application.class, args);
		System.setProperty("spring.config.name", "job-web");
		System.out.println("Spring boot REST API for Job is start here:  http://localhost:8081/");
	}
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/aboutus").setViewName("aboutus");
	}
	@Bean
	public ApplicationRunner jobsInitializer(JobRepository jobRepository) {
		return args -> {		
			if(jobRepository.findAll().size() == 0) {
			
			jobRepository.saveAndFlush(new Job(1, "j0001", "Branch Manager", "Manage a 100 person branch", new Date(), 3));
			jobRepository.saveAndFlush(new Job(2, "j0002", "VP", "Manage the cloud software engineer department", new Date(), 1));
			jobRepository.saveAndFlush(new Job(3, "j0003", "Financial product Manager", "provide service for our mutual fund", new Date(), 10));
			jobRepository.saveAndFlush(new Job(4, "j0003", "Financial product Manager", "provide service for our mutual fund", new Date(), 10));
			jobRepository.saveAndFlush(new Job(5, "j0002", "Software Developer", "IT cloud software development and support", new Date(), 5));
			}							
		};
	}
	
}

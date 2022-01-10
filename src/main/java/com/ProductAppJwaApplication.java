package com;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author Jesse
 * 		The SpringBootApplication annotation does the following:
 *Sets up default config (to change default, edit the application.properties file)
 *Starts Spring ApplicationContext
 *Performs class path scanning (creates beans)
 *Starts Tomcat Server
 */
@SpringBootApplication
public class ProductAppJwaApplication {

	/**
	 * @param args
	 * String beans[] is printing out all of the beans that are created.
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProductAppJwaApplication.class, args);
		
		String beans[] = ctx.getBeanDefinitionNames();
		Arrays.sort(beans);
		for(String beanNames:beans) {
			System.out.println(beanNames);
		}
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}

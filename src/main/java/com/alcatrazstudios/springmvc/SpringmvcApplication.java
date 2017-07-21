package com.alcatrazstudios.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(SpringmvcApplication.class, args);
//
//		System.out.println("Beans **************");
//		System.out.println(ctx.getBeanDefinitionCount());
//		for (String name : ctx.getBeanDefinitionNames()){
//			System.out.println(name);
//		}
	}
}

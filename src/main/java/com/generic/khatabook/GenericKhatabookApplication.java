package com.generic.khatabook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {
//		"com.generic.khatabook.repository",
//		"com.generic.khatabook.controller",
//		"com.generic.khatabook.model",
//		"com.generic.khatabook.entity",
//		"com.generic.khatabook.service",
//		"com.generic.khatabook.service.impl"
//		})
@ComponentScan(basePackages = {"com.generic.*"})
@EnableJpaRepositories("com.generic.khatabook.repository")
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DBConnect.class})
public class GenericKhatabookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenericKhatabookApplication.class, args);
    }


//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//		return sessionFactory;
//	}
}

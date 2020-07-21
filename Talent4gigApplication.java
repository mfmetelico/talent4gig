package com.example.talent4gig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Talent4gigApplication {

	public static void main(String[] args) {
		SpringApplication.run(Talent4gigApplication.class, args);
	}

}
*/

@SpringBootApplication
public class Talent4gigApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Talent4gigApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Talent4gigApplication.class, args);
    }
}
package com.acorn.springboardteacher;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@PropertySources(@PropertySource("classpath:/env.properties"))
public class SpringBoardTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoardTeacherApplication.class, args);
	}

}

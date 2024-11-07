package com.PS29729;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import vn.payos.PayOS;


@SpringBootApplication
@Configuration
public class DuantotnghiepApplication implements WebMvcConfigurer  {
	private String clientId ="7c921bb0-0143-4dff-a507-d72a6d0a24e8";

	private String apiKey = "76443134-a796-4943-96cc-59a3309f4454";

	private String checksumKey = "501eb77ddf212aeb2c84a66a0c92b02520cf8fdaa5c15e006f5bea4ed3b8c913";

	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600); 
	}

	@Bean
	public PayOS payOS() {
		return new PayOS(clientId, apiKey, checksumKey);
	}

	public static void main(String[] args) {
		SpringApplication.run(DuantotnghiepApplication.class, args);
	}

}

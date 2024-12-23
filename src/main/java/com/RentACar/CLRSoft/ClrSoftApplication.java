package com.RentACar.CLRSoft;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ClrSoftApplication {

	public static int  userPermission =0;
	public static int userOrCustomerId=0;
	public static int discount=20;

	public static void main(String[] args) {
		SpringApplication.run(ClrSoftApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
}

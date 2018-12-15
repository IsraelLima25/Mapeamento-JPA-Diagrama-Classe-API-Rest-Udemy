package com.curso.mc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.mc.service.DBService;

@Configuration
@Profile("desenv")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean istantieteDataBase() throws ParseException {
		dbService.instantieteTestDataBase();
		return true;
	}

}

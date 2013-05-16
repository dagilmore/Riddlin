package com.riddlin.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Profile("local")
@PropertySource(value={"classpath:localConfig.properties"})
public class LocalConfig {

}
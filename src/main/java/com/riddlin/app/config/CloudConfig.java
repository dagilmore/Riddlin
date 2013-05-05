
package com.riddlin.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@Profile("cloud")
@PropertySource(value={"classpath:cloudConfig.properties"})
public class CloudConfig {
    @Inject
    private Environment environment;

}

package com.riddlin.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.comfirm.alphamail.services.client.AlphaMailService;
import com.comfirm.alphamail.services.client.DefaultAlphaMailService;


@Configuration
@Profile("cloud")
@PropertySource(value={"classpath:cloudConfig.properties", "classpath:alphamail.properties"})
public class CloudConfig {
    @Inject
    private Environment environment;

    @Bean
    public AlphaMailService alphaMailService() {
        DefaultAlphaMailService mailService = new DefaultAlphaMailService();
        mailService.setServiceUrl(environment.getProperty("alphamail.url"));
        mailService.setApiToken(environment.getProperty("alphamail.token"));
        return mailService;
    }
}
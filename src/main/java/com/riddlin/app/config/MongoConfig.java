package com.riddlin.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories(basePackages = "com.riddlin.app.domain")
public class MongoConfig extends AbstractMongoConfiguration {
    @Inject
    private Environment environment;
    
    @Override
    public String getDatabaseName() {
        return environment.getProperty("mongodb.dbname");
    }
    
    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(environment.getProperty("mongodb.username"), environment.getProperty("mongodb.password"));
    }

    @Override
    public String getMappingBasePackage() {
        return "com.riddlin.app.domain";
    }

    @Override
    public Mongo mongo() throws Exception {
        Mongo mongo = new Mongo("localhost");
        mongo.setWriteConcern(WriteConcern.SAFE);
        return mongo;
    }
}

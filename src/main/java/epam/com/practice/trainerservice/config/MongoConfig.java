package epam.com.practice.trainerservice.config;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import epam.com.practice.trainerservice.repo.TrainerWorkloadRepository;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories(basePackageClasses = {TrainerRepository.class, TrainerWorkloadRepository.class,
TrainingRepository.class})
public class MongoConfig {

    @Value(value = "${spring.data.mongodb.uri}")
    private String url;

    @Value(value = "${spring.data.mongodb.database}")
    private String db;

    @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), db);
    }

    @Bean
    MongoClient mongoClient() {
        ConnectionString connectionString
                = new ConnectionString(url);

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder -> builder.maxSize(20)
                        .minSize(10)
                        .maxWaitTime(2000, TimeUnit.MILLISECONDS)
                        .build())
                .build();

        return MongoClients.create(mongoClientSettings);
    }

}

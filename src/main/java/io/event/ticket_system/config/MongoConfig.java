package io.event.ticket_system.config;

import io.event.ticket_system.util.MongoOffsetDateTimeReader;
import io.event.ticket_system.util.MongoOffsetDateTimeWriter;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingEntityCallback;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Configuration
@EnableMongoRepositories("io.event.ticket_system.repository")
public class MongoConfig {

    @Bean
    public MongoTransactionManager transactionManager(final MongoDatabaseFactory databaseFactory) {
        return new MongoTransactionManager(databaseFactory);
    }

    @Bean
    public ValidatingEntityCallback validatingEntityCallback(
            final LocalValidatorFactoryBean factory) {
        return new ValidatingEntityCallback(factory);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new MongoOffsetDateTimeWriter(),
                new MongoOffsetDateTimeReader()
                ));
    }

}

package com.nakedgardener.configuration;

import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class LocalDateTimeJacksonConverter {
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.modulesToInstall(new JSR310Module());
        return builder;
    }
}

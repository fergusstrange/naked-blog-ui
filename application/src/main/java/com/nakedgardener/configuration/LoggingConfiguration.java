package com.nakedgardener.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    @Bean
    public Log applicationErrorLog() {
        return LogFactory.getLog("naked-gardener-error.log");
    }
}

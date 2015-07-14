package com.nakedgardener.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class TestWebSecurityConfiguration {

    @Bean
    public WebSecurityConfigurer webSecurityConfigurer() {
        return new WebSecurityConfigurerAdapter() {};
    }
}

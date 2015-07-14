package com.nakedgardener.configuration;

import com.nakedgardener.web.BasePageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BasePageInterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private BasePageInterceptor basePageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basePageInterceptor);
    }
}

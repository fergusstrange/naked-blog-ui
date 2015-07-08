package com.nakedgardener;

import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@EnableWebMvcSecurity
@SpringBootApplication
@ComponentScan("com.nakedgardener")
public class Application {
    public static void main(String... args) {
        new SpringApplicationBuilder()
                .sources(Application.class)
                .listeners(new ApplicationPidFileWriter("naked-gardener.pid"))
                .build()
                .run(args);
    }
}

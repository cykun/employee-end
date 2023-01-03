package com.cyquen.employee;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Application {

    public static void main(String[] args) {
        var app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLazyInitialization(true);
        app.run(args);
    }
}

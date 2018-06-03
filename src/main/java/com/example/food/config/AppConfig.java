package com.example.food.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://stackoverflow.com/questions/45744181/how-to-prevent-hibernate5-from-lazy-fetching-when-jackson-serializes-json-object
@Configuration
public class AppConfig {
    @Bean
    protected Module module() {
        return new Hibernate5Module();
    }
}

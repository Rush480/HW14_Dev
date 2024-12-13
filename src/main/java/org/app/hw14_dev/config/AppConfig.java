package org.app.hw14_dev.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean// ObjectMapper
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}

package ru.practicum.main_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.practicum.client.EventClient;

@Configuration
public class ConfigMainService {

    @Bean
    public EventClient eventClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {

        return new EventClient(serverUrl, builder);
    }
}

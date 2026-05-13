package com.jiwon.datagg.riot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "riot.api")
public record RiotApiProperties(
        String key,
        String accountBaseUrl,
        String krBaseUrl
) {
}

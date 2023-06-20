package com.techshop.config.properties.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api.properties")
public record ApiProperties(ApiUserDetails[] users) {
}

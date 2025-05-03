package com.cnsia.cloud_native_spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cnsia")
public class CloudNativeSpringConfig {
    
    private String application_name;

    public String getApplicationName() {
        return application_name;
    }

    public void setApplicationName(String application_name) {
        this.application_name = application_name;
    }
    
}

package com.sourceallies.gildedlotus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {
    @Bean
    public CommonsRequestLoggingFilter filterToAddTomcatAccessLogsAndRequestURIs() {
        var loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setIncludePayload(false);
        loggingFilter.setIncludeQueryString(true);
        return loggingFilter;
    }
}

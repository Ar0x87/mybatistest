package com.null01.configuration;

import com.null01.interceptors.GlobalResultHandler;
import com.null01.services.IWrapperService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@AllArgsConstructor
public class GlobalResultHandlerAutoConfiguration {
    private IWrapperService wrapperService;

    @Bean
    @ConditionalOnMissingBean
    public GlobalResultHandler responseWrapperAdvice() {
        return new GlobalResultHandler(wrapperService);
    }
}

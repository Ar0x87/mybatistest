package com.null01.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class HotelServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    HotelServiceInterceptor hotelServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hotelServiceInterceptor);
    }
}

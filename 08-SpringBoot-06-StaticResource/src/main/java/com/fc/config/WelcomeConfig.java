//package com.fc.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WelcomeConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/home.html");
//
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
//}
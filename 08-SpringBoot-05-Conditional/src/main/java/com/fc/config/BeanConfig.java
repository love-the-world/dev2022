package com.fc.config;

import com.fc.entity.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean("car")
    @ConditionalOnMissingBean({Bike.class, Car.class})
    @ConditionalOnBean(Person.class)
    public Car getCar() {
        Car car1 = new Car();

        car1.setBrand("666");

        return car1;
    }

    @Bean("car2")
    public Car getCar2() {
        Car car2 = new Car();

        car2.setBrand("nb");

        return car2;
    }

    @Bean("wife")
    public Wife getWife() {
        return new Wife();
    }

    @Bean("son")
    @ConditionalOnBean(Wife.class)
    public Son getSon() {
        return new Son();
    }

    @Bean("girlFriend")
    @ConditionalOnMissingBean(Wife.class)
    public GirlFriend getGirlFriend() {
        return new GirlFriend();
    }
}

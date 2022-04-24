package com.fc.config;

import com.fc.entity.Car;
import com.fc.entity.Person;
import com.fc.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Configuration(proxyBeanMethods = false)
public class TestConfig {
    @Bean
    public Person person(Car car) {
        return new Person("王豪祥", car);
    }

    @Bean("car")
    public Car getCar() {
        return new Car("保时捷", "无色");
    }

    @Bean
    @Scope("prototype")
    public User getUser() {
        return new User();
    }

    public TestConfig(){
        System.out.println("构造方法被执行");
    }
    public String test(){
        System.out.println();
        return "一口一个张曹慧";
    }

    //被@PostConstruct声明的方法会在服务器加载时直接执行
    //构造方法以及@Autowired之后执行，会在Servlet的init之前执行
    //只要是JavaWeb框架都能使用这个注解
    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }
}

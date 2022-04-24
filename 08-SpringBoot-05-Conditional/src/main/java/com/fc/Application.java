package com.fc;

import com.fc.entity.Car;
import com.fc.entity.GirlFriend;
import com.fc.entity.Son;
import com.fc.entity.Wife;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        if (applicationContext.containsBean("wife")) {
            applicationContext.getBean(Wife.class);
            System.out.println("有老婆");
        } else {
            System.out.println("没老婆");
        }

        if (applicationContext.containsBean("girlFriend")) {
            applicationContext.getBean(GirlFriend.class);
            System.out.println("有对象！！！");
        } else {
            System.out.println("你没对象！！！");
        }

        if (applicationContext.containsBean("son")) {
            applicationContext.getBean(Son.class);
            System.out.println("有儿子");
        } else {
            System.out.println("没儿子");
        }

        Car car = applicationContext.getBean("car2", Car.class);

        System.out.println(car);
    }

}

package com.fc;

import com.fc.config.TestConfig;
import com.fc.entity.Car;
import com.fc.entity.Dog;
import com.fc.entity.Person;
import com.fc.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 1.用于启动SpringBoot项目
        // 2.用于获取SpringBoot容器
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        String[] names = applicationContext.getBeanDefinitionNames();

        for (String name : names) {
            System.out.println(name);
        }

        //从容其中获取对象
        TestConfig testConfig = applicationContext.getBean(TestConfig.class);

        System.out.println("hashcode:" + testConfig.hashCode());
        System.out.println("地址:" + testConfig);

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);

        System.out.println("容器中的对象：" + (user1 == user2));

        User user4 = testConfig.getUser();
        User user3 = testConfig.getUser();

        System.out.println("new出来的：" + (user3 == user4));
        System.out.println("容器中的和new出来的：" + (user1 == user4));

        System.out.println("------------------------------------------");

        Person person = applicationContext.getBean(Person.class);

        Car car = applicationContext.getBean(Car.class);

        Car car1 = testConfig.getCar();

        person.setCar(car1);

        System.out.println("人的车和容器中的车是否一样：" + (person.getCar() == car));

        Car carcar = applicationContext.getBean("car", Car.class);

        System.out.println(carcar);

        System.out.println("---------------------");

        Dog dog = applicationContext.getBean("com.fc.entity.Dog", Dog.class);

        System.out.println("从容其中拿的狗" + dog);
    }

}

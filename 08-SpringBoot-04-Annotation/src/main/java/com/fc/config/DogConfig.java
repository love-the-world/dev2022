package com.fc.config;

import com.fc.entity.Dog;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Dog.class)
public class DogConfig {
}

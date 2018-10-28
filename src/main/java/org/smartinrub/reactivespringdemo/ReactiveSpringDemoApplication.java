package org.smartinrub.reactivespringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringDemoApplication.class, args);
    }
}

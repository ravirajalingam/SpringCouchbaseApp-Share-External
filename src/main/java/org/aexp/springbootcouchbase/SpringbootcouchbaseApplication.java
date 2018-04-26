package org.aexp.springbootcouchbase;

import org.aexp.springbootcouchbase.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootcouchbaseApplication {

    @Autowired
    private UserRepository userRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootcouchbaseApplication.class, args);
    }
}

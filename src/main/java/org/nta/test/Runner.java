package org.nta.test;


import org.nta.test.services.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;


@SpringBootApplication()
public class Runner implements CommandLineRunner {

    public static void main(String[] args) {
       SpringApplication.run(Runner.class, args);
    }
@Autowired
private Main main;
    @Override
    public void run(String... args) {
     main.doAllWork();
    }

}

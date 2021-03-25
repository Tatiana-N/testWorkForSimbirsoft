package org.nta.test;

import org.nta.test.api.Splittable;
import org.nta.test.nopac.Main;
import org.nta.test.nopac.Read_From_Url_Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@SpringBootApplication()
public class Runner implements CommandLineRunner {

    public static void main(String[] args) {
       SpringApplication.run(Runner.class, args);
    }
@Autowired
private Main main;

    private URL url;

    @Override
    public void run(String... args) throws Exception {
     main.doAllWork();
    }

}

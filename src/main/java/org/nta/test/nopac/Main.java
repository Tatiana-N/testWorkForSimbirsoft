package org.nta.test.nopac;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import org.nta.test.api.Splittable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Main {

    private static final Logger LOGGER = MyLogger.getMyLogger(Main.class);
    @Autowired
    private Saver saver;
    @Autowired
    private Read_From_Url_Runner read;

    public Main() {
    }

    public void doAllWork() {
        System.out.println("Enter URL: ");
        Scanner scanner = new Scanner(System.in);
        URL url = null;
        try {
            url = new URL(scanner.nextLine());
        } catch (MalformedURLException e) {
            LOGGER.warn(e);
        }
        read.setUrl(url);
        read.read();
        Map<String, Integer> map = read.getMap();
        saver.saveToDB(map);
        System.out.println(map.size());
        saver.getFromDB();
    }
}

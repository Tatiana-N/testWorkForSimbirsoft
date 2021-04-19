package org.nta.test.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.nta.test.api.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Main {

    private static final Logger LOGGER = MyLogger.getMyLogger(Main.class);
    @Autowired
    private SaverImpl saverImpl;
    @Autowired
    private Reader<String> read;

    public Main() {
    }

    public void doAllWork() {
        System.out.println("Enter URL: ");
        Scanner scanner = new Scanner(System.in);
        URL url = null;
        try {
            url = new URL(scanner.nextLine());
            LOGGER.info("Сайт из которого получаем текст : " + url.toString());
        } catch (MalformedURLException e) {
            LOGGER.warn(e);
        }
        read.setUrl(url);
        read.read();
        Map<String, Integer> map = read.getMap();
        saverImpl.saveToDB(map);
        saverImpl.getFromDB();
    }
}

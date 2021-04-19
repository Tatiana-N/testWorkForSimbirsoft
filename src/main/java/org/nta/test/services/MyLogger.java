package org.nta.test.services;

import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

@Getter
public class MyLogger {
    public static <T> Logger getMyLogger(Class<T> tClass) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Logger.getLogger(tClass);
    }
}

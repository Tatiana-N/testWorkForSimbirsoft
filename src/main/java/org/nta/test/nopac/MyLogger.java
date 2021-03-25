package org.nta.test.nopac;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

@Getter
@Component
@NoArgsConstructor
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

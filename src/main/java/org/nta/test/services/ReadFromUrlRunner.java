package org.nta.test.services;

import lombok.Setter;
import org.nta.test.api.Reader;
import org.nta.test.api.Splittable;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


@Getter
@Setter
@Component
public class ReadFromUrlRunner implements Reader<String> {
    List<String> list;
    private static final Logger LOGGER = MyLogger.getMyLogger(ReadFromUrlRunner.class);
    Splittable<String, Integer> splitter;
    URL url;
    Map<String, Integer> map;

    public ReadFromUrlRunner(Splittable<String, Integer> splitter) {
        this.splitter = splitter;
        list = new ArrayList<>(Arrays.asList("style", "script"));
        LOGGER.info("Создали reader url");
    }

    @Override
    public void read() {
        LOGGER.info("читаем со страницы");
        map = new HashMap<>();
        String string;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while (true) {
                string = r.readLine().trim();
                if (string.startsWith("<body")) {
                    break;
                }
                // пропускаем head
            }
            while (string != null) {
                for (String s : list) {
                    if (string.trim().startsWith("<" + s)) {
                        while (!string.contains("</" + s)) {
                            string = r.readLine();
                        }
                        string = "";
                    }
                    if (string.trim().startsWith("<")) {
                        while (!string.endsWith("/>") && !string.endsWith(">")) {
                            sb.append(string);
                            string = r.readLine();
                        }
                        string = sb.toString() + string;
                        sb = new StringBuilder();
                    }
                }
                map.putAll(splitter.split(string));
                string = r.readLine();
            }
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }
}
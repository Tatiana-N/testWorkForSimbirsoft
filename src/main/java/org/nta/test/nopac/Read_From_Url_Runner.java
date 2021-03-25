package org.nta.test.nopac;

import lombok.Setter;
import org.nta.test.api.Splittable;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


@Getter
@Setter
@Component
public class Read_From_Url_Runner<T> {
    List<String> list;
    private static final Logger LOGGER = MyLogger.getMyLogger(Read_From_Url_Runner.class);
    Splittable <T,Integer> splitter;
    URL url;
    Map<T, Integer> map;

    public Read_From_Url_Runner(Splittable<T,Integer> splitter) {
        this.splitter = splitter;
        list = new ArrayList<>(Arrays.asList("style", "script"));
        LOGGER.info("Создали reader url");
    }

    public void read() {
        LOGGER.info("читаем со страницы");
        map = new HashMap<>();
        String string;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while (!(string = r.readLine()).trim().startsWith("<body")) {
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
                String str = string.replaceAll("(?s)<!--.+?-->|<script.+?</script>|<.+?>", "")
                        .replaceAll("&nbsp;| +", " ")
                        .replaceAll("\t ", " ")
                        .replaceAll("\n ", " ")
                        .replaceAll("\r ", " ");
                if (!str.trim().equals("")) {
                    map.putAll( splitter.split(str));
                }
                string = r.readLine();
            }
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }
}
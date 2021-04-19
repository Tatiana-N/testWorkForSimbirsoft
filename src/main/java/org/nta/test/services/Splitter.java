package org.nta.test.services;

import org.nta.test.api.Splittable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Splitter implements Splittable<String, Integer> {
    Map<String, Integer> words = new HashMap<>();
    private static final Logger LOGGER = MyLogger.getMyLogger(Splitter.class);

    public Splitter() {
        LOGGER.info("Создан объект Splitter");
    }

    @Override
    public Map<String, Integer> split(String text) {
        text = regex(text);
        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            //  group = group.replaceAll("\\d+", "");// надо удалять цифры?
            if (!group.equals("")) {
                String trim = group.trim().toUpperCase(Locale.ROOT);
                if (words.containsKey(trim)) {
                    words.put(trim, words.get(trim) + 1);
                } else {
                    words.put(trim, 1);
                }
            }
        }
        return words;
    }

    private String regex(String string) {
        return string.replaceAll("(?s)<!--.+?-->|<script.+?</script>|<.+?>", "")
                .replaceAll("&nbsp;| +", " ")
                .replaceAll("\t ", " ")
                .replaceAll("\n ", " ")
                .replaceAll("\r ", " ").trim();
    }
}

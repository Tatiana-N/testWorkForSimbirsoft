package org.nta.test.api;

import java.net.URL;
import java.util.Map;

public interface Reader<T> {
    void read();

    Map<T, Integer> getMap();

    void setUrl(URL url);
}

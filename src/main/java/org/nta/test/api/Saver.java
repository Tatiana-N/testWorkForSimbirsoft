package org.nta.test.api;

import java.util.Map;

public interface Saver<T> {
    void saveToDB(Map<T, Integer> map);

    void getFromDB();
}

package org.nta.test.api;

import java.util.Map;

public interface Splittable<T, Integer> {
    Map<T, Integer> split(String text);
}

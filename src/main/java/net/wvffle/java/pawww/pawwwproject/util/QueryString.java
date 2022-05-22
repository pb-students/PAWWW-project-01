package net.wvffle.java.pawww.pawwwproject.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QueryString {
    private final Map<String, String> query = new HashMap<>();

    public void remove(String name) {
        query.remove(name);
    }

    public void add(String name, String value) {
        if (value != null) {
            query.put(name, value);
            return;
        }

        remove(name);
    }

    private String encode(String name, String value) {
        return URLEncoder.encode(name, StandardCharsets.UTF_8)
            + "="
            + URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    public String toString() {
        StringBuilder qs = new StringBuilder();
        for (Map.Entry<String, String> entry : query.entrySet()) {
            if (qs.length() > 0) {
                qs.append("&");
            }

            qs.append(encode(entry.getKey(), entry.getValue()));
        }

        return "?" + qs.toString();
    }
}


package com.ruby.cafeshop;

import java.util.HashMap;
import java.util.Map;

public abstract class CafeShop {

    private final Map<String, Object> caffeMap = new HashMap<>();

    public final <T> T getCafe(String name) {
        return (T)caffeMap.get(name);
    }

    public final void setCafe(String name, Object cafe) {
        caffeMap.put(name, cafe);
    }
}

package com.graduation.votingsystem.util;

public class Util {
    private Util() {
    }

    public static <T> T orElse(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}

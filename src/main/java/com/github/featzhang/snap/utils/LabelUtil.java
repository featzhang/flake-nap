package com.github.featzhang.snap.utils;

import java.util.ResourceBundle;

public class LabelUtil {
    private static final ResourceBundle BUNDLE;

    static {
        BUNDLE = ResourceBundle.getBundle("flake/flake-nap");
    }

    public static String label(String key) {
        return BUNDLE.getString(key);
    }

    public static int intLabel(String key) {
        return Integer.parseInt(label(key));
    }

    public static long longLabel(String key) {
        return Long.parseLong(label(key));
    }
}

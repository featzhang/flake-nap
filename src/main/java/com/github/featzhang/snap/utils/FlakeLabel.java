package com.github.featzhang.snap.utils;

import java.util.ResourceBundle;

public enum FlakeLabel {

    APP_NAME,
    START_WORK,
    START_REST,
    STOP,
    TIMER_DEFAULT,
    THINGS_DEFAULT,
    INIT_STATE,
    WORKING,
    RESTING,
    WAITING,
    ;


    private final String value;

    private FlakeLabel() {
        String name = this.name();
        String key = name.replaceAll("_", ".").toLowerCase();
        this.value = LabelUtil.label(key);
    }

    public String value() {
        return value;
    }

}

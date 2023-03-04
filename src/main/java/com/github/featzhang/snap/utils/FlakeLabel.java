package com.github.featzhang.snap.utils;

import java.util.ResourceBundle;

public enum FlakeLabel {

    APP_NAME("app.name"),
    START("start"),
    STOP("stop"),
    TIMER_DEFAULT("timer.default.value"),
    THINGS_DEFAULT("things.default.value"),
    ;


    private final String value;

    private FlakeLabel(String key) {
        this.value = LabelUtil.label(key);
    }

    public String value() {
        return value;
    }

}

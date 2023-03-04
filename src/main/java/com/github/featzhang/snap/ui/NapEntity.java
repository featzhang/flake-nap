package com.github.featzhang.snap.ui;

import lombok.Data;

import java.io.Serializable;

@Data
public class NapEntity implements Serializable {
    private long startTime;

    private long endTime;

    private String title;
}

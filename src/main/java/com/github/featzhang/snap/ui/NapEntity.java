package com.github.featzhang.snap.ui;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class NapEntity implements Serializable {
    private long startTime;

    private long endTime;

    private String title;
}

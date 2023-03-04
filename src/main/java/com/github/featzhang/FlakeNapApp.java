package com.github.featzhang;

import com.github.featzhang.snap.ui.SnapFrame;

import javax.swing.*;

public class FlakeNapApp {
    public static void main(String[] args) {
        SnapFrame snapFrame = new SnapFrame();
        snapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        snapFrame.setLocationRelativeTo(null);
        snapFrame.setSize(400, 400);
        snapFrame.setVisible(true);
    }
}

package org.example;

import javax.swing.*;

public class ToloApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToloGameFrame();
        });
    }
}


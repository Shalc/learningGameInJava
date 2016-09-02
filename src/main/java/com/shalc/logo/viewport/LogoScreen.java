package com.shalc.logo.viewport;

import javax.swing.*;

/**
 * @author David Emidio
 */
public class LogoScreen extends JFrame {

    public LogoScreen() {
        init();
    }

    private void init() {
        add(new LogoBoard());

        setResizable(false);
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

package com.shalc.spaceship.viewport;

import javax.swing.*;

/**
 * @author David Emidio
 */
public class SpaceshipScreen extends JFrame {

    public SpaceshipScreen() {
        initUI();
    }

    private void initUI() {
        add(new SpaceshipBoard());

        setResizable(false);
        pack();

        setTitle("Spaceship");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
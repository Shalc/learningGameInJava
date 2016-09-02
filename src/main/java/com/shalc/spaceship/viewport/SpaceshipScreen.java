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
        add(new Board());

        setSize(400, 300);
        setResizable(false);

        setTitle("Jogin");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
package com.shalc;

import javax.swing.*;

/**
 * @author David Emidio
 */
public class Application extends JFrame {

    public Application() {
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
package com.shalc.spaceship;

import com.shalc.spaceship.viewport.SpaceshipScreen;

import java.awt.*;

/**
 * @author David Emidio
 */
public class SpaceshipStart {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new SpaceshipScreen().setVisible(true));
    }

}
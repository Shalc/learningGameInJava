package com.shalc.logo;

import com.shalc.logo.viewport.LogoScreen;

import java.awt.*;

/**
 * @author David Emidio
 */
public class LogoStart {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new LogoScreen().setVisible(true));
    }

}

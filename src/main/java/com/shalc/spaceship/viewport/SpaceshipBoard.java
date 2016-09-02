package com.shalc.spaceship.viewport;

import com.shalc.spaceship.sprite.Alien;
import com.shalc.spaceship.sprite.Craft;
import com.shalc.spaceship.sprite.Missile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Emidio
 */
public class SpaceshipBoard extends JPanel implements ActionListener {


    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;

    private final int[][] pos = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };

    private Timer timer;
    private Craft craft;
    private boolean ingame;
    private ArrayList<Alien> aliens;

    public SpaceshipBoard() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        aliens = new ArrayList<>();

        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            drawObjects(g);
        } else {
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        }

        craft.getMissiles().forEach(missile -> g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this));
        aliens.stream().filter(Alien::isVisible).forEach(alien -> g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this));

        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);

        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = getFontMetrics(font);

        g.setFont(font);

        String message = "Game Over";
        g.drawString(message, (B_WIDTH - fontMetrics.stringWidth(message)) / 2, B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();

        updateCraft();
        updateMissiles();
        updateAliens();

        checkCollisions();

        repaint();
    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateCraft() {
        if (craft.isVisible()) {
            craft.move();
        }
    }

    private void updateMissiles() {
        List<Missile> ms = craft.getMissiles();
        ms.removeIf(missile -> !missile.isVisible());
        ms.forEach(Missile::move);
    }

    private void updateAliens() {
        if (aliens.isEmpty()) {
            ingame = false;
        } else {
            aliens.removeIf(alien -> !alien.isVisible());
            aliens.forEach(Alien::move);
        }
    }


    private void checkCollisions() {
        Rectangle craftBounds = craft.getBounds();
        aliens.forEach(alien -> {
            if (craftBounds.intersects(alien.getBounds())) {
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        });

        craft.getMissiles().forEach(missile -> {
            aliens.forEach(alien -> {
                if (alien.getBounds().intersects(missile.getBounds())) {
                    missile.setVisible(false);
                    alien.setVisible(false);
                }
            });
        });
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}

package com.shalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author David Emidio
 */
public class Board extends JPanel implements ActionListener {


    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private Craft craft;

    public Board() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        craft.getMissiles().forEach(missile -> g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateMissiles();
        updateCraft();
        repaint();
    }

    private void updateMissiles() {
        List<Missile> ms = craft.getMissiles();
        ms.stream().filter(Missile::isVisible).forEach(Missile::move);
        ms.removeIf(missile -> !missile.isVisible());
    }

    private void updateCraft() {
        craft.move();
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

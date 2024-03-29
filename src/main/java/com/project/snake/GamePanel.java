/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.snake;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author roycerabanal
 */
public class GamePanel extends JPanel implements ActionListener {

    boolean continueGame = false;
    boolean applePainted = false;
    boolean gameOver = false;
    boolean paused = false;
    boolean showPausedText = false;

    // Screen dimensions
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    // Snake's speed
    static final int DELAY = 80;

    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        startGame();

    }

    public void startGame() {

        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void restartGame() {
        
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        running = false;
        newApple();
        startGame();
        
    }

    public void resetGame() {
        
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        running = false;
        gameOver = false;
        applePainted = false;

        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        newApple();
        
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (running) {
            draw(g);
        } else {
            gameOver(g);
            if (!applePainted) {
                    g.setColor(Color.red);
                    g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
                    applePainted = true;              
            } 
        }
        
        if(showPausedText) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 75));
            FontMetrics metrics = g.getFontMetrics();
            String pausedText = "PAUSED";
            int x = (SCREEN_WIDTH - metrics.stringWidth(pausedText)) / 2;
            int y = SCREEN_HEIGHT / 2;
            g.drawString(pausedText, x, y);
        }

    }

    public void draw(Graphics g) {

        if (running) {

            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {

                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }

            }
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        } else {
            gameOver(g);
        }

    }

    public void newApple() {

        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

    }

    public void move() {

        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple() {

        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }

    }

    public void checkCollisions() {

        // check if head collides with body
        for (int i = bodyParts; i > 0; i--) {

            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }

        }

        // check if head touches left border
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH) {
            running = false;
        }
        // right border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        // top border
        if (y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }
        // bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
            gameOver = true;
            repaint();
        }

    }

    public void gameOver(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        //Game Over Text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
        
        //Restart option
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Restart? (y/n)", (SCREEN_WIDTH - metrics3.stringWidth("Restart? (y/n)")) / 2, SCREEN_HEIGHT / 2 + 100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        
        if(!running && continueGame) {
            restartGame();
        }
        repaint();

    }

    public class myKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if(running) {
                        if(!paused) {
                            timer.stop();
                            paused = true;
                            showPausedText = true;
                        } else {
                            paused = false;
                            showPausedText = false;
                            timer.start();
                            move();
                        }
                        repaint();
                    }
                    break;
            }
            
            if(!running) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_Y:
                        if(gameOver) {
                            resetGame();
                            startGame();
                        }
                        break;
                    case KeyEvent.VK_N:
                        System.exit(0);
                        break;
                }
            }

        }

    }

}

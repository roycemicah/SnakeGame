/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.snake;

import javax.swing.JFrame;

/**
 *
 * @author roycerabanal
 */
public class GameFrame extends JFrame {

//    GameFrame() {
//        this.add(new GamePanel());
//        this.setTitle("Snake");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
//        this.pack();
//        this.setVisible(true);
//        this.setLocationRelativeTo(null);
//    }
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private StartMenuPanel startMenuPanel;
    private GamePanel gamePanel;

    public GameFrame() {

        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        startMenuPanel = new StartMenuPanel();
        add(startMenuPanel);

        pack();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void startGame() {
        remove(startMenuPanel);
        
        gamePanel = new GamePanel();
        add(gamePanel);
        
        gamePanel.requestFocus();
        
        pack();
        validate();
    }

}

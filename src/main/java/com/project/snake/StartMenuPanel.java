/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.snake;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author roycerabanal
 */
public class StartMenuPanel extends JPanel {
    
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    
    private final JButton newGameButton;
    
    public StartMenuPanel() {
        setLayout(null);
        setBackground(Color.black);
        
        newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        
        newGameButton.setBounds((SCREEN_WIDTH - 150) / 2, (SCREEN_HEIGHT - 80) / 2, 150, 80);
        newGameButton.setBackground(Color.white);
        
        newGameButton.addActionListener(e -> {
            GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
            gameFrame.startGame();
        });
        
        add(newGameButton);
    }
}

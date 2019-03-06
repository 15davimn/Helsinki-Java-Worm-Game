/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.gui;

import java.awt.Color;
import javax.swing.JPanel;
import wormgame.domain.*;
import java.awt.Graphics;
import java.util.List;
import wormgame.game.WormGame;

/**
 *
 * @author madison.davis
 */
public class DrawingBoard extends JPanel implements Updatable {

    private Worm worm;
    private int pieceLength;
    private WormGame game;

    public DrawingBoard(WormGame game, int pieceLength) {
        super.setBackground(Color.GRAY);
        this.game = game;
        this.worm = game.getWorm();
        this.pieceLength = pieceLength;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        List<Piece> pieces = worm.getPieces();
        for (Piece currentPiece : pieces) {
            graphics.setColor(Color.BLACK);
            graphics.fill3DRect(currentPiece.getX() * pieceLength, currentPiece.getY() * pieceLength, pieceLength, pieceLength, true);
        }
        graphics.setColor(Color.RED);
        Apple apple = game.getApple();
        graphics.fillOval(apple.getX() * pieceLength, apple.getY() * pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
        super.repaint();
    }

}

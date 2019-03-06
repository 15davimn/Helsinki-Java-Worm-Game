package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.gui.Updatable;
import wormgame.domain.*;
import java.util.ArrayList;
import java.util.List;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;
        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);
        makeApple();

        addActionListener(this);
        setInitialDelay(2000);

    }

    public Worm getWorm() {
        return worm;
    }

    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public void makeApple() {
        while (true) {
            this.apple = new Apple(new Random().nextInt(width - 2) + 1, new Random().nextInt(height - 2) + 1);
            boolean equalsPiece = false;
            List<Piece> pieces = worm.getPieces();
            for (Piece currentPiece : pieces) {
                if (currentPiece.runsInto(apple)) {
                    equalsPiece = true;
                }
            }
            if (!(equalsPiece)) {
                break;
            }
        }
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        worm.move();

        List<Piece> pieces = worm.getPieces();
        if (worm.runsInto(apple)) {
            worm.grow();
            makeApple();
        }
        
        if (worm.runsIntoItself()) {
            continues = false;
        }
        
        Piece finalPiece = pieces.get(pieces.size() - 1);
         if (finalPiece.getX() <= 0  || finalPiece.getX() >= width || finalPiece.getY() <= 0 || finalPiece.getY() >= height) {
             continues = false;
         }
        
        updatable.update();
        
       setDelay(1000 / worm.getLength());

    }

}

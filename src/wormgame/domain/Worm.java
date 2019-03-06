/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import wormgame.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author madison.davis
 */
public class Worm {

    private Direction direction;
    private List<Piece> pieces;
    private int maxSize;

    public Worm(int x, int y, Direction originalDirection) {
        this.direction = originalDirection;
        this.pieces = new ArrayList<Piece>();
        pieces.add(new Piece(x, y));
        maxSize = 3;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public int getLength() {
        return pieces.size();
    }

    public Piece newPiece() {
        Piece head = pieces.get(pieces.size() - 1);
        if (direction == Direction.DOWN) {
            return (new Piece(head.getX(), head.getY() + 1));
        } else if (direction == Direction.LEFT) {
            return (new Piece(head.getX() - 1, head.getY()));
        } else if (direction == Direction.RIGHT) {
            return (new Piece(head.getX() + 1, head.getY()));
        } else {
            return (new Piece(head.getX(), head.getY() - 1));
        }
    }

    public void grow() {
        if (pieces.size() >= 3) {
            maxSize++;
        }
    }

    public boolean runsInto(Piece piece) {
        for (Piece currentPiece : pieces) {
            if (piece.runsInto(currentPiece)) {
                return true;
            }
        }
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean runsIntoItself() {
        for (int spotChecking = 0; spotChecking < pieces.size(); spotChecking++) {
            Piece pieceChecking = pieces.get(spotChecking);
            for (int currentPiece = (spotChecking + 1); currentPiece < pieces.size(); currentPiece++) {
                if (pieceChecking.runsInto(pieces.get(currentPiece))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move() {
        if (pieces.size() < maxSize) {
            pieces.add(newPiece());
        } else {
            Piece toAdd = newPiece();
            pieces.remove(0);
            pieces.add(toAdd);
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }

}

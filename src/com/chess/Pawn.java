package com.chess;

import java.awt.*;

public class Pawn extends Piece{

    private int direction;


    public Pawn(Team team, int direction){
        super(team);
        this.direction = direction;
    }


    public boolean canMoveTo(int[] oldPos, int[] newPos, BoardData boardData) {

        Piece pieceMovedTo = boardData.getPiece(newPos);

        //this could be done better but i dont care rn

        if (oldPos[0] == newPos[0] && pieceMovedTo == null){
            if (!isMoved() && oldPos[1] + 2 * direction == newPos[1]){
                return true;
            }
            if (oldPos[1] + direction == newPos[1]){
                return true;
            }
        }

        else if (Math.abs(oldPos[0] - newPos[0]) == 1 && pieceMovedTo != null && pieceMovedTo.getTeam() != getTeam()){
            if (oldPos[1] + direction == newPos[1]){
                return true;
            }
        }

        return false;

    }

    public void draw(Graphics g, int x, int y){

        String imageName = getTeam().getColor() + "_pawn";

        g.drawImage(Main.images.get(imageName), x, y, Main.tileSize, Main.tileSize, null);
    }
}

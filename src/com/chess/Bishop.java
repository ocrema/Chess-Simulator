package com.chess;

import java.awt.*;

public class Bishop extends Piece{

    public Bishop(Team team){
        super(team);
    }


    public boolean canMoveTo(int[] oldPos, int[] newPos, BoardData boardData) {

        //change in x and y
        int cX = newPos[0] - oldPos[0];
        int cY = newPos[1] - oldPos[1];

        if (Math.abs(cX) != Math.abs(cY)){
            return false;
        }

        //direction of x and y
        int dX = Math.abs(cX) / cX;
        int dY = Math.abs(cY) / cY;

        for (int i = 1; i < Math.abs(cX); i++){
            if (boardData.getPiece(oldPos[0] + i * dX, oldPos[1] + i * dY) != null){
                return false;
            }
        }

        return true;
    }

    public void draw(Graphics g, int x, int y){

        String imageName = getTeam().getColor() + "_bishop";

        g.drawImage(Main.images.get(imageName), x, y, Main.tileSize, Main.tileSize, null);
    }
}

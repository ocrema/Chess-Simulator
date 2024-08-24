package com.chess;

import java.awt.*;

public class King extends Piece{

    public King(Team team){
        super(team);
    }


    public boolean canMoveTo(int[] oldPos, int[] newPos, BoardData boardData){

        if ((Math.abs(oldPos[0] - newPos[0]) <= 1 && Math.abs(oldPos[1] - newPos[1]) <= 1))
        {
            return true;
        }
        return false;
    }

    public void draw(Graphics g, int x, int y){

        String imageName = getTeam().getColor() + "_king";

        g.drawImage(Main.images.get(imageName), x, y, Main.tileSize, Main.tileSize, null);
    }
}

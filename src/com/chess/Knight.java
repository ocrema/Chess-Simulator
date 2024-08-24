package com.chess;

import java.awt.*;

public class Knight extends Piece{

    public Knight(Team team){
        super(team);
    }


    public boolean canMoveTo(int[] oldPos, int[] newPos, BoardData boardData) {

        if (oldPos[0] == newPos[0] || oldPos[1] == newPos[1]){
            return false;
        }

        if (Math.abs(oldPos[0] - newPos[0]) + Math.abs(oldPos[1] - newPos[1]) != 3){
            return false;
        }

        return true;
    }

    public void draw(Graphics g, int x, int y){

        String imageName = getTeam().getColor() + "_knight";

        g.drawImage(Main.images.get(imageName), x, y, Main.tileSize, Main.tileSize, null);
    }
}

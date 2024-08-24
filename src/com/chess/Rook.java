package com.chess;

import java.awt.*;

public class Rook extends Piece{

    public Rook(Team team){
        super(team);
    }


    public boolean canMoveTo(int[] oldPos, int[] newPos, BoardData boardData){

        if (oldPos[0] == newPos[0]){

            int min = Math.min(oldPos[1], newPos[1]);
            int max = Math.max(oldPos[1], newPos[1]);

            for (int i = min + 1; i < max; i++){
                if (boardData.getPiece(oldPos[0], i) != null){
                    return false;
                }
            }
            return true;
        }

        else if (oldPos[1] == newPos[1]){

            int min = Math.min(oldPos[0], newPos[0]);
            int max = Math.max(oldPos[0], newPos[0]);

            for (int i = min + 1; i < max; i++){
                if (boardData.getPiece(i, oldPos[1]) != null){
                    return false;
                }
            }
            return true;
        }

        return false;

    }

    public void draw(Graphics g, int x, int y){

        String imageName = getTeam().getColor() + "_rook";

        g.drawImage(Main.images.get(imageName), x, y, Main.tileSize, Main.tileSize, null);
    }
}

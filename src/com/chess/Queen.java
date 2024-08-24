package com.chess;

import java.awt.*;

public class Queen extends Piece{

    public Queen(Team team){
        super(team);
    }

    @Override
    public boolean canMoveTo(int[] oldPos, int[] newPos, BoardData boardData) {

        //bishop movement
        //change in x and y
        int cX = newPos[0] - oldPos[0];
        int cY = newPos[1] - oldPos[1];

        if (Math.abs(cX) == Math.abs(cY)) {

            //direction of x and y
            int dX = Math.abs(cX) / cX;
            int dY = Math.abs(cY) / cY;

            for (int i = 1; i < Math.abs(cX); i++) {
                if (boardData.getPiece(oldPos[0] + i * dX, oldPos[1] + i * dY) != null) {
                    return false;
                }
            }

            return true;
        }
        //rook movement

        else if (oldPos[0] == newPos[0]){

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

        String imageName = getTeam().getColor() + "_queen";

        g.drawImage(Main.images.get(imageName), x, y, Main.tileSize, Main.tileSize, null);
    }
}

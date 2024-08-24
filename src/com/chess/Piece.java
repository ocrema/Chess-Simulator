package com.chess;

import java.awt.*;

public abstract class Piece {

    private Team team;

    private boolean moved;

    public Piece(Team team){
        this.team = team;
        moved = false;
    }

    public Team getTeam(){
        return team;
    }

    public void moved(){
        moved = true;
    }

    public boolean isMoved(){
        return moved;
    }

    public abstract void draw(Graphics g, int x, int y);

    //checks if a piece can move to location based on the piece's rules
    //assumes that the piece on the tile moved to is not itself or on the same team
    public abstract boolean canMoveTo(int[] oldPos, int[] newPos, BoardData boardData);
}

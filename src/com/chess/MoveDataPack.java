package com.chess;

//used to easily move data about possible moves inbetween methods
public class MoveDataPack {

    private BoardData oldBoardData;
    private BoardData newBoardData;
    private Piece piece;
    private int[] oldPos;
    private int[] newPos;
    private Team teamThatMoved;
    private Team otherTeam;

    public MoveDataPack(BoardData oldBoardData, int[] oldPos, int[] newPos, Team teamThatMoved, Team otherTeam){

        this.oldBoardData = oldBoardData;
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.teamThatMoved = teamThatMoved;
        this.otherTeam = otherTeam;

        piece = oldBoardData.getPiece(oldPos);

        newBoardData = oldBoardData.copy();
        newBoardData.changePiece(oldPos, null);
        newBoardData.changePiece(newPos, piece);

    }

    public MoveDataPack(BoardData oldBoardData, BoardData newBoardData, int[] oldPos, int[] newPos, Team teamThatMoved, Team otherTeam){

        this.oldBoardData = oldBoardData;
        this.newBoardData = newBoardData;
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.teamThatMoved = teamThatMoved;
        this.otherTeam = otherTeam;

        piece = oldBoardData.getPiece(oldPos);



    }

    public BoardData getOldBoardData(){
        return oldBoardData;
    }

    public BoardData getNewBoardData(){
        return newBoardData;
    }

    public Piece getPiece() {
        return piece;
    }

    public int[] getNewPos() {
        return newPos;
    }

    public int[] getOldPos() {
        return oldPos;
    }

    public Team getTeamThatMoved() {
        return teamThatMoved;
    }

    public Team getOtherTeam() {
        return otherTeam;
    }
}

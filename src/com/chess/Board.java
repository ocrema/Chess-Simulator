package com.chess;

import java.awt.*;
import java.util.LinkedList;

public class Board {

    private BoardData boardData;

    private int xOffset;
    private int yOffset;

    private Team white;
    private Team black;
    private Team activeTeam;
    private Team teamInCheck;
    private Team winner;

    //the piece a player is dragging around
    private Piece selectedPiece;


    private LinkedList<Effect> effects;



    public Board(int xOffset, int yOffset){

        this.xOffset = xOffset;
        this.yOffset = yOffset;

        white = new Team("white");
        black = new Team("black");
        activeTeam = white;
        teamInCheck = null;
        winner = null;

        selectedPiece = null;

        effects = new LinkedList<>();

        //adding pieces
        boardData = new BoardData(8, 8);
        boardData.defaultFill(white, black);
        //boardData.changePiece(4, 4, new Queen(white));

        //testing stuff
        /*
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                if (!Main.dataAnalyzer.isValidMove(new MoveDataPack(boardData, new int[]{4, 4}, new int[]{x, y}, white, black))){

                    int newY = Math.abs(8 - y -1);

                    effects.add(new Effect(x * Main.tileSize + Main.tileSize/2 + xOffset,
                            newY  * Main.tileSize + Main.tileSize/2 + yOffset,
                            Main.tileSize, Main.tileSize, 30000, "x"));
                }
            }
        }
         */


    }

    public void clicked(int mX, int mY){

        if (mouseOnBoard(mX, mY)){

            if (selectedPiece == null) {
                int boardX = (mX - xOffset) / Main.tileSize;
                int boardY = (mY - yOffset) / Main.tileSize;

                //reverse y
                boardY = Math.abs(boardY - Main.boardHeight + 1);

                Piece piece = boardData.getPiece(boardX, boardY);
                if (piece != null && activeTeam == piece.getTeam()) {
                    selectedPiece = boardData.getPiece(boardX, boardY);
                }
            }
            else {
                //if mouse is released off screen, clicking will act as a release
                released(mX, mY);
            }
        }
    }

    public void released(int mX, int mY){

        if (mouseOnBoard(mX, mY) && selectedPiece != null){

            int boardX = (mX - xOffset) / Main.tileSize;
            int boardY = (mY - yOffset) / Main.tileSize;

            //reverse y
            boardY = Math.abs(boardY - Main.boardHeight + 1);


            // old position of moved piece
            int[] oldPos = boardData.getPiece(selectedPiece);
            // position piece moved too
            int[] newPos = {boardX, boardY};

            Team teamThatMoved = selectedPiece.getTeam();
            Team otherTeam = white;
            if (teamThatMoved == otherTeam){
                otherTeam = black;
            }
            MoveDataPack pack = new MoveDataPack(boardData, oldPos, newPos, teamThatMoved, otherTeam);

            //look for special moves
            //pack = Main.dataAnalyzer.checkSpecialMoves();


            //check if move is valid
            BoardData result = Main.dataAnalyzer.getBoardAfterMove(pack);

            if (result != null) {

                //piece has been moved
                boardData = result;
                selectedPiece.moved();
                teamInCheck = null;

                if (activeTeam == white){
                    activeTeam = black;
                }
                else{
                    activeTeam = white;
                }

                if (Main.dataAnalyzer.isInCheck(boardData, otherTeam, teamThatMoved)){
                    teamInCheck = otherTeam;
                    //System.out.println("check");

                    if (!Main.dataAnalyzer.canMateBeAvoided(boardData, otherTeam, teamThatMoved)){
                        winner = teamThatMoved;
                        //System.out.println("mate");
                    }
                }



            }
            else if (oldPos[0] != newPos[0] || oldPos[1] != newPos[1]) {

                //re - reverse y
                boardY = Math.abs(boardY - Main.boardHeight + 1);

                effects.add(new Effect(boardX * Main.tileSize + Main.tileSize / 2 + xOffset,
                        boardY * Main.tileSize + Main.tileSize / 2 + yOffset,
                        Main.tileSize, Main.tileSize, 300, "x"));
            }
            selectedPiece = null;
        }


    }



    public boolean mouseOnBoard(int mX, int mY){
        return (mX >= xOffset && mX < xOffset + boardData.getWidth() * Main.tileSize &&
                mY >= yOffset && mY < yOffset + boardData.getHeight() * Main.tileSize);
    }

    public String[] getHeaderText(){

        String[] result = new String[2];

        if (winner != null){
            result[0] = winner.getColor() + " Wins!";
            result[0] = result[0].substring(0, 1).toUpperCase() + result[0].substring(1);
        }
        else if (teamInCheck != null){
            result[0] = teamInCheck.getColor() + " in Check";
            result[0] = result[0].substring(0, 1).toUpperCase() + result[0].substring(1);
        }
        else {
            result[0] = "";
        }


        result[1] = activeTeam.getColor() + "'s Turn";
        result[1] = result[1].substring(0, 1).toUpperCase() + result[1].substring(1);


        return result;
    }


    public void clearEffects(){
        effects.clear();
    }


    public void draw(Graphics g){

        for (int x = 0; x < Main.boardWidth; x++){
            for (int y = 0; y < Main.boardHeight; y++){

                //reverse y to make the board right side up
                int rY = Math.abs(y - Main.boardHeight + 1);

                //squares
                if ((x + y) % 2 == 0){
                    g.setColor(new Color(39, 39, 39));
                }
                else{
                    g.setColor(new Color(210, 210, 210));
                }

                g.fillRect(x * Main.tileSize + xOffset, rY * Main.tileSize + yOffset, Main.tileSize, Main.tileSize);


                //pieces
                Piece piece = boardData.getPiece(x, y);
                //                       V selected piece is drawn later
                if (piece != null && piece != selectedPiece){
                    piece.draw(g, x * Main.tileSize + xOffset, rY * Main.tileSize + yOffset);
                }
            }
        }

        //effects
        for (Effect effect : effects){
            if (effect.checkForDeletion()){
                effects.remove(effect);
            }
            else{
                effect.draw(g);
            }
        }

        //draw selected piece at mouse
        if (selectedPiece != null){
            selectedPiece.draw(g, Main.keyManager.getMouseX() - Main.tileSize/2, Main.keyManager.getMouseY() - Main.tileSize/2);
        }
    }


    public Team getWinner(){
        return winner;
    }
}

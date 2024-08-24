package com.chess;

public class BoardData {

    private Piece[][] tiles;

    public BoardData(int width, int height){

        tiles = new Piece[width][height];
    }

    public BoardData copy(){
        BoardData newData = new BoardData(getWidth(), getHeight());

        for (int x = 0; x < getWidth(); x++){
            for (int y = 0; y < getHeight(); y++){
                newData.changePiece(x, y, tiles[x][y]);
            }
        }
        return newData;
    }

    public void defaultFill(Team white, Team black){


        for (int i = 0; i < 8; i++){
            tiles[i][1] = new Pawn(white, 1);
            tiles[i][6] = new Pawn(black, -1);
        }

        tiles[0][0] = new Rook(white);
        tiles[7][0] = new Rook(white);
        tiles[0][7] = new Rook(black);
        tiles[7][7] = new Rook(black);

        tiles[1][0] = new Knight(white);
        tiles[6][0] = new Knight(white);
        tiles[1][7] = new Knight(black);
        tiles[6][7] = new Knight(black);

        tiles[2][0] = new Bishop(white);
        tiles[5][0] = new Bishop(white);
        tiles[2][7] = new Bishop(black);
        tiles[5][7] = new Bishop(black);

        tiles[3][0] = new Queen(white);
        tiles[3][7] = new Queen(black);

        tiles[4][0] = new King(white);
        tiles[4][7] = new King(black);

    }

    public int[] getPiece(Piece piece){

        for (int x = 0; x < getWidth(); x++){
            for (int y = 0; y < getHeight(); y++){
                if (tiles[x][y] == piece){
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    public Piece getPiece(int x, int y){
        return tiles[x][y];
    }

    public Piece getPiece(int[] pos){
        return tiles[pos[0]][pos[1]];
    }

    public void changePiece(int x, int y, Piece piece){
        tiles[x][y] = piece;
    }

    public void changePiece(int[] pos, Piece piece){
        tiles[pos[0]][pos[1]] = piece;
    }

    public int getWidth(){
        return tiles.length;
    }

    public int getHeight(){
        return tiles[0].length;
    }
}

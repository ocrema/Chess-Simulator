package com.chess;

public class DataAnalyzer {

    //returns the board after piece move has been moved
    //returns null if move is not valid
    public BoardData getBoardAfterMove(MoveDataPack pack){

        if (isValidMove(pack)) {

            return pack.getNewBoardData();
        }
        else{
            return null;
        }
    }

    public boolean isValidMove(MoveDataPack pack){
        return isValidPieceMove(pack) && !isInCheck(pack.getNewBoardData(), pack.getTeamThatMoved(), pack.getOtherTeam());
    }

    //checks if a piece could move based on moving rules (not checkmate)
    public boolean isValidPieceMove(MoveDataPack pack){

        // unpack pack
        BoardData oldBoardData = pack.getOldBoardData();
        BoardData newBoardData = pack.getNewBoardData();
        Piece piece = pack.getPiece();
        int[] oldPos = pack.getOldPos();
        int[] newPos = pack.getNewPos();


        //cannot move onto teammate and cannot move onto self
        if (oldBoardData.getPiece(newPos) != null && oldBoardData.getPiece(newPos).getTeam() == piece.getTeam()){
            return false;
        }

        //piece specific movement
        if (!piece.canMoveTo(oldPos, newPos, oldBoardData)){
            return false;
        }

        return true;
    }

    //checks if given board is in check
    public boolean isInCheck(BoardData boardData, Team team, Team otherTeam){

        Piece piece;
        int[] kingPos = null;

        //find king
        for (int x = 0; x < boardData.getWidth(); x++) {
            for (int y = 0; y < boardData.getHeight(); y++) {

                Piece temp = boardData.getPiece(x, y);
                if (temp instanceof King && temp.getTeam() == team){
                    kingPos = new int[]{x, y};
                }
            }
        }

        for (int x = 0; x < boardData.getWidth(); x++){
            for (int y = 0; y < boardData.getHeight(); y++){

                piece = boardData.getPiece(x, y);
                if (piece != null && piece.getTeam() == otherTeam){
                    if (isValidPieceMove(new MoveDataPack(boardData, new int[]{x, y}, kingPos, otherTeam, team))){

                        return true;
                    }
                }
            }
        }

        return false;
    }


    public boolean canMateBeAvoided(BoardData boardData, Team team, Team otherTeam){

        int[] kingPos = null;

        //find king
        for (int x = 0; x < boardData.getWidth(); x++) {
            for (int y = 0; y < boardData.getHeight(); y++) {

                Piece temp = boardData.getPiece(x, y);
                if (temp instanceof King && temp.getTeam() == team){
                    kingPos = new int[]{x, y};
                }
            }
        }

        //each possible king move
        for (int x = kingPos[0] - 1; x <= kingPos[0] + 1; x++){
            for (int y = kingPos[1] - 1; y <= kingPos[1] + 1; y++){

                if (x >= 0 && x < boardData.getWidth() &&
                        y >= 0 && y < boardData.getHeight()){

                    if (isValidMove(new MoveDataPack(boardData, kingPos, new int[]{x, y}, team, otherTeam))){
                        return true;
                    }
                }
            }
        }


        Piece piece;
        for (int x = 0; x < boardData.getWidth(); x++){
            for (int y = 0; y < boardData.getHeight(); y++){

                piece = boardData.getPiece(x, y);
                if (piece != null && piece.getTeam() == team && !(piece instanceof King)){

                    for (int x2 = 0; x2 < boardData.getWidth(); x2++) {
                        for (int y2 = 0; y2 < boardData.getHeight(); y2++) {

                            if (isValidMove(new MoveDataPack(boardData, new int[]{x, y}, new int[]{x2, y2}, team, otherTeam))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    /*
    //checks if move is special, and alters new board acordingly
    public MoveDataPack checkSpecialMoves(MoveDataPack pack){

        // unpack pack
        BoardData oldBoardData = pack.getOldBoardData();
        BoardData newBoardData = pack.getNewBoardData();
        Piece piece = pack.getPiece();
        int[] oldPos = pack.getOldPos();
        int[] newPos = pack.getNewPos();
        Team team = pack.getTeamThatMoved();
        Team otherTeam = pack.getOtherTeam();

        MoveDataPack newPack = pack;

        //pawn ranking up
        if (piece instanceof Pawn && (newPos[1] == 0 || newPos[1] == newBoardData.getHeight() - 1)){
            newBoardData.changePiece(newPos, new Queen(team));
            newPack = new MoveDataPack(oldBoardData, newBoardData, oldPos, newPos, team, otherTeam);
        }
        //castle
        else if ()


        return newPack;
    }

     */
}

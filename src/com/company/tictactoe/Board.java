package com.company.tictactoe;

public class Board {
    Field[][] board = new Field[3][3];
    int[] maxPlayerPieces = new int[2];

    public Board() {
        initBoard(0, 0, 3);
        initBoard(0, 1, 2);
        initBoard(0, 2, 3);
        initBoard(1, 0, 2);
        initBoard(1, 1, 4);
        initBoard(1, 2, 2);
        initBoard(2, 0, 3);
        initBoard(2, 1, 2);
        initBoard(2, 2, 3);
    }

    private void initBoard(int x, int y, int value) {
        Field field = new Field();
        field.setOwner(0);
        field.setValue(value);
        board[x][y] = field;
    }

    public boolean setOwner(int x, int y, int player) {
        if (x < 0 || x > 2 || board[x][y].getOwner() != 0 || maxPlayerPieces[player - 1] >= 3) {
            return false;
        } else {
            board[x][y].setOwner(player);
            return true;
        }
    }

    public boolean removeOwner ( int x, int y){
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            return false;
        } else {
            board[x][y].setOwner(0);

        }
        return true;
    }

    public int getValue(int x, int y){
        return board[x][y].getValue();
    }

    public int getOwner(int x, int y){
        return board[x][y].getOwner();
    }

    public boolean movePiece ( int fromX, int fromY, int toX, int toY, int player){
        //if the coordinates are out of range OR the player does not owne the Field OR the destination field is not free or if not all three pieces for the player
        //has been put on the board.
        if (fromX < 0 || fromX > 2 || fromY < 0 || fromY > 2 || board[fromX][fromY].getOwner() != player || board[toX][toY].getOwner() != 0 || maxPlayerPieces[player - 1] < 3) {
            return false;
        } else {
            removeOwner(fromX, fromY);
            setOwner(toX, toY, player);
        }
        return true;
    }

    public void increaseNrOfPlayingPiecesFor (int player){
        maxPlayerPieces[player - 1] = maxPlayerPieces[player - 1] + 1;
    }

    public void printBoard(){
        StringBuilder boardString = new StringBuilder();

        for(int y = 0; y<3;y++){
                for(int x = 0; x<3;x++){
                    switch (board[x][y].getOwner()){
                        case 0:
                            boardString.append("-");
                            break;
                        case 1:
                            boardString.append("X");
                            break;
                        case 2:
                            boardString.append("O");
                    }
                }
                boardString.append("\n");
        }
    }

    public int calculateBoardValue(Board board, int player){
        int boardValue = checkForWin(board, player);
        int horizontalWin = 0;
        //check Corners
        for (int y = 0; y<3;y++){
            for (int x = 0; x<3; x++){

                if (board.getOwner(x,y)==player){
                    horizontalWin = horizontalWin + 1;
                    boardValue=boardValue+board.getValue(x,y);
                }
                if (horizontalWin==3) {
                    boardValue = 100;
                }
            }
        }
        return boardValue;
    }

    private int checkForWin(Board board, int player){



        return 100;
    }
}

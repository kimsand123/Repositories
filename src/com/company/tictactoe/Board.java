package com.company.tictactoe;

public class Board {
    int numberOfFields=12;
    String board[] = new String[numberOfFields];
    int numberOfTokensOnTheBoard;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        for (int taller = 0;taller<numberOfFields;taller++) {
            board[taller] = "0";
        }
    }

    public void setStaticValue(int value){
        board[9] = String.valueOf(value);
    }
    public void setAlpha(int alpha){
        board[10] = String.valueOf(alpha);
    }
    public void setBeta(int beta){
        board[11] = String.valueOf(beta);
    }


    public int getStaticValue(){
        return Integer.parseInt(board[9]);
    }
    public int getAlpha(){
        return Integer.parseInt(board[10]);
    }

    public int getBeta(){
        return Integer.parseInt(board[11]);
    }

    public void setOwner(int index, int player) {
        board[index] = String.valueOf(player);
    }

    public int getOwner(int index){
        return Integer.parseInt(board[index]);
    }

    public void removeOwner ( int index){
        board[index] = "0";
    }

    public int getValue(int index){
        if(index==0 || index == 2 || index == 6 || index == 8){
            return 3;
        }
        if(index == 4){
            return 4;
        }
        return 2;
    }

    public void movePiece ( int fromIndex, int toIndex){
        int player = getOwner(fromIndex);
        removeOwner(fromIndex);
        setOwner(toIndex, player);
    }

    public void printBoard(){
        System.out.println("Start TicTacToe");
        StringBuilder boardString = new StringBuilder();

        for(int index = 0; index<9;index++){

            switch (getOwner(index)){
                case 0:
                    boardString.append("-");
                    break;
                case 1:
                    boardString.append("X");
                    break;
                case 2:
                    boardString.append("O");
                    break;
            }
            if((index+1)%3==0){
                boardString.append("\n");
            }
        }
        System.out.println(boardString.toString());
    }

    public int calculateBoardValue(){
        int boardValue=0;
        int playerValue=0;
        int otherPlayerValue=0;
        //check Board
        for (int index = 0; index<9;index++){
            if (getOwner(index)==1){
                boardValue=boardValue+getValue(index);
            }
        }
        if(checkForWin(board, 1)){
            boardValue=100;
        }
        playerValue=boardValue;
        //Check boardvalue for otherplayer
        boardValue=0;
        //check Corners
        for (int index = 0; index<9;index++){
            if (getOwner(index)==2){
                boardValue=boardValue+getValue(index);
            }
        }
        if(checkForWin(board, 2)){
            boardValue=-100;
        }


        setStaticValue(playerValue-otherPlayerValue);
        return getStaticValue();
    }

    private int getOtherPlayer(int player){
        if (player == 1){
            return 2;
        }
        return 1;
    }

    private boolean checkForWin(String[] board, int player) {
        //Vandrette wins
        for (int y=0;y<3;y++) {
            if (Integer.parseInt(board[0 + y * 3]) == player && Integer.parseInt(board[1 + y * 3]) == player && Integer.parseInt(board[2 + y * 3]) == player) {
                return true;
            }
        }
        //Lodrette wins
        for (int x= 0;x<3;x++) {
            if (Integer.parseInt(board[0+x]) == player && Integer.parseInt(board[3+x]) == player && Integer.parseInt(board[6+x]) == player) {
                return true;
            }
        }
        //Skrå wins
        if (Integer.parseInt(board[0])==player && Integer.parseInt(board[4])==player && Integer.parseInt(board[8])==player){
            return true;
        }
        if (Integer.parseInt(board[2])==player && Integer.parseInt(board[4])==player && Integer.parseInt(board[6])==player){
            return true;
        }



        return false;
    }

    public void recordAIMove(Board board){
        for (int taller = 0;taller<9;taller++){
            setOwner(taller, board.getOwner(taller));
        }
    }

    public void addNumberOfTokensToTheBoard(){
        this.numberOfTokensOnTheBoard = this.numberOfTokensOnTheBoard + 1;
    }

    public int getNumberOfTokensOnTheBoard (){
        return this.numberOfTokensOnTheBoard;
    }
}

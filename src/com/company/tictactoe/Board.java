package com.company.tictactoe;

public class Board {
    int numberOfFields=10;
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

    public int getStaticValue(){
        return Integer.parseInt(board[9]);
    }
    public int getAlpha(){
        return Integer.parseInt(board[10]);
    }

    public int getBeta(){
        return Integer.parseInt(board[11]);
    }

    public void setOwner(int player, int index) {
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
        setOwner(player, toIndex);
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

    public int calculateBoardValue(int depth){
        int player1BoardValue = getPlayerBoardValue(board,1,depth);
        int player2BoardValue = getPlayerBoardValue(board,2,depth);
        setStaticValue(player1BoardValue-player2BoardValue);
        return getStaticValue();
    }

    private int getPlayerBoardValue(String[] board, int player, int depth) {
        //Vandrette calc
        int exponent = 1;
        int boardValue = 0;
        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++) {
                if (Integer.parseInt(board[y * 3+x]) == player) {
                    boardValue = boardValue + (int) Math.pow(getValue(y * 3+x), exponent);
                    exponent++;
                    exponent++;
                }else {
                    //reset eksponenten da striben i så fald er brudt.
                    exponent=1;
                }
            }
        }
        exponent=1;
        //Lodret calc
        for (int y = 0; y<3;y++) {
            for (int x = 0; x < 3; x++) {
                if (Integer.parseInt(board[ 0 + x+(y*3)])==player)  {
                    boardValue = boardValue + (int)Math.pow(getValue(0 + x+(y*3)),exponent);
                    exponent++;
                    exponent++;
                }else {
                    exponent=1;
                }
            }
        }

        //Skrå calculation
        int step= 0;
        exponent=1;
        for (int taller = 0;taller<3;taller++){
            if (Integer.parseInt(board[step])==player){
                boardValue = boardValue + (int)Math.pow(getValue(step),exponent);
                exponent++;
                exponent++;

            }
            step=step+4;
        }

        step = 2;
        exponent=1;
        for(int taller=0;taller<3;taller++){
            if(Integer.parseInt(board[step])==player){
                boardValue = boardValue + (int)Math.pow(getValue(step),exponent);
                exponent++;
                exponent++;
            }

            step=step+2;
        }
        return boardValue-depth*5;
    }

    public void recordAIMove(Board board){
        for (int taller = 0;taller<9;taller++){
            setOwner(taller, board.getOwner(taller));
        }
    }

    public void addNumberOfTokensToTheBoard(){
        this.numberOfTokensOnTheBoard = this.numberOfTokensOnTheBoard + 1;
    }

    public int getNumberOfTokensFromTheBoard(){
        return this.numberOfTokensOnTheBoard;
    }

    public String[] getBoardData() {
        String[] boardData = new String[10];
        for (int taller = 0; taller<9;taller++){
            boardData[taller] = board[taller];
        }
        return boardData;
    }

    public void setBoardData(String[] boardData){
        for (int taller = 0; taller<9;taller++){
            board[taller] = boardData[taller];
        }
    }

}

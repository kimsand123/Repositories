package com.company.tictactoe;

public class Board {
    Field[] board = new Field[10];
    int numberOfTokensOnTheBoard=0;

    public Board() {
        initBoard(0,  3);
        initBoard(1,  2);
        initBoard(2,  3);
        initBoard(3,  2);
        initBoard(4,  4);
        initBoard(5,  2);
        initBoard(6,  3);
        initBoard(7,  2);
        initBoard(8,  3);
        initBoard(9,0);//The static value of the board
    }

    private void initBoard(int index, int value) {
        Field field = new Field();
        field.setOwner(0);
        field.setValue(value);
        board[index] = field;
    }

    public void setStaticValue(int value){
        Field valueField = new Field();
        valueField.setValue(value);
        board[9]=valueField;
    }

    public int getStaticValue(){
        return board[9].getValue();
    }

    public void setOwner(int index, int player) {
        board[index].setOwner(player);
    }

    public int getOwner(int index){
        return board[index].getOwner();
    }

    public void removeOwner ( int index){
        board[index].setOwner(0);
    }

    public int getValue(int index){
        return board[index].getValue();
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

            switch (board[index].getOwner()){
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

    public int calculateBoardValue(int player){
        int boardValue=0;
        //check Corners
        for (int index = 0; index<9;index++){
            if (getOwner(index)==player){
                boardValue=boardValue+getValue(index);
            }
        }
        if(checkForWin(board, player)){
            boardValue=100;
        }

        setStaticValue(boardValue);
        return getStaticValue();
    }

    private boolean checkForWin(Field[] board, int player) {
        //Vandrette wins
        for (int y=0;y<3;y++) {
            if (board[0 + y * 3].getOwner() == player && board[1 + y * 3].getOwner() == player && board[2 + y * 3].getOwner() == player) {
                return true;
            }
        }
        //Lodrette wins
        for (int x= 0;x<3;x++) {
            if (board[0+x].getOwner() == player && board[3+x].getOwner() == player && board[6+x].getOwner() == player) {
                return true;
            }
        }
        //Skrå wins
        if (board[0].getOwner()==player && board[4].getOwner()==player && board[8].getOwner()==player){
            return true;
        }
        if (board[2].getOwner()==player && board[4].getOwner()==player && board[6].getOwner()==player){
            return true;
        }



        return false;
    }

    public void recordAIMove(Board board){
        for (int taller = 0;taller<9;taller++){
            setOwner(taller, board.getOwner(taller));
        }
    }

    public void setNumberOfTokensOnTheBoard(int numberOfTokensOnTheBoard){
        this.numberOfTokensOnTheBoard = numberOfTokensOnTheBoard;
    }

    public int getNumberOfTokensOnTheBoard (){
        return this.numberOfTokensOnTheBoard;
    }
}

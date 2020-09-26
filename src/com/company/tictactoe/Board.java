package com.company.tictactoe;

public class Board {
    Field[] board = new Field[10];

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
    }

    public int calculateBoardValue(Board board, int player){
        int boardValue=0, horizontalWin=0, verticalWin=0, yx1=0, yx2 = 0, yx3 = 0;
        //check Corners
        for (int index = 0; index<9;index++){
                if (board.getOwner(index)==player){
                    boardValue=boardValue+board.getValue(index);
                }
            }
            if (boardValue==8 || boardValue==10) {
                boardValue = 100;
            }
        return boardValue;
        }
}

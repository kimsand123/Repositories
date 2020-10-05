package com.company.tictactoe;

public class Board {
    int numberOfFields=10;
    String board[] = new String[numberOfFields];
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
        int player1BoardValue = getPlayerBoardValue(1,depth);
        int player2BoardValue = getPlayerBoardValue(2,depth);
        setStaticValue(player1BoardValue-player2BoardValue);
        return getStaticValue();
    }
    private int getPlayerBoardValue(int player, int depth){
        int playerBoardValue = gameWon(player);
        int fieldValue = 0;
        if (gameWon(player)==200){
            playerBoardValue = 200;
        } else {
            for (int taller = 0; taller<9;taller++){
                if (this.getOwner(taller)==player){
                    fieldValue = this.getValue(taller);
                    playerBoardValue=playerBoardValue+fieldValue;
                }
            }
        }
        return playerBoardValue-2*depth;
    }

    private int gameWon(int player) {
        //skulle være lavet med forlykker, men var lige nød til at idiotsikre det.
        //horisontale
        if(this.getOwner(0) == player && this.getOwner(1) == player && this.getOwner(2)==player){
            return 200;
        }
        if(this.getOwner(3)== player && this.getOwner(4)==player && this.getOwner(5)==player){
            return 200;
        }
        if(this.getOwner(6)==player && this.getOwner(7)==player && this.getOwner(8)==player){
            return 200;
        }
        //vertikale
        if(this.getOwner(0)==player && this.getOwner(3)==player && this.getOwner(6)==player){
            return 200;
        }
        if (this.getOwner(1)==player && this.getOwner(4)==player && this.getOwner(7)==player){
            return 200;
        }
        if (this.getOwner(2)==player && this.getOwner(5)==player && this.getOwner(8)==player){
            return 200;
        }
        //Skrå
        if (this.getOwner(0)==player && this.getOwner(4)==player && this.getOwner(8)==player){
            return 200;
        }
        if (this.getOwner(2)==player && this.getOwner(4)==player && this.getOwner(6)==player){
            return 200;
        }

        return 0;
    }

    public Board copyBoard(Board boardToCopyTo){
        for (int taller = 0;taller<9;taller++){
            boardToCopyTo.setOwner(getOwner(taller),taller);
        }
        return boardToCopyTo;
    }

    public int getNumberOfTokensFromTheBoard(){
        int tokensOnBoard=0;
        for (int taller = 0;taller<9;taller++){
            if(getOwner(taller)!=0){
                tokensOnBoard++;
            }
        }
        return tokensOnBoard;
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

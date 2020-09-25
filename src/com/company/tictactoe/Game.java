package com.company.tictactoe;

import java.util.Scanner;

public class Game {
    int players;
    int humanPlayer;
    int firstPlayer;
    Board board = new Board();
    int[] cord = new int[2];
    Scanner input = new Scanner(System.in);
    String s;
    String[] cordString;

    public void Game(int players, int humanPlayer, int firstPlayer){
        this.players = players;
        this.humanPlayer = humanPlayer;
        this.firstPlayer = firstPlayer;
        startGame(firstPlayer);
        board.printBoard();
    }

    private void startGame(int player){

        while (true){
            if (board.maxPlayerPieces[player]<3){
                if (player == humanPlayer){
                    System.out.println("Enter x,y coordinate for putting a playing piece");
                    s = input.nextLine();
                    cordString=s.split(",");
                    cord[0]=Integer.parseInt(cordString[0]);
                    cord[1]=Integer.parseInt(cordString[1]);
                    //get Coordinates for placing piece into cord. 0=x, 1=y
                }else{
                    //Do the AlphaBetaThing and get Coordinates for placing piece into cord. 0=x, 1=y
                }
                //If the move goes through change to new player otherwise just repeat loop;
                if(threeFirstRounds(player, cord[0], cord[1])){
                    player = getNewPlayer(player);
                }
            } else
                if (player == humanPlayer){
                    //move piece from one field to another.
                } else {
                    //Have computerplayer move piece from one field to another.
                }



            board.printBoard();
        }

    }

    private int getNewPlayer(int player){
        if (player == 1){
            return 2;
        }
        return 1;
    }

    public boolean threeFirstRounds(int x, int y, int player){
        board.increaseNrOfPlayingPiecesFor(player);
        return board.setOwner(x,y,player);
    }

    public void normalRounds(){

    }

    public Board boardCreator(Board board, int player){
        Board hypotheticalBoard = new Board();


        return hypotheticalBoard;
    }

    public int boardEvaluator(Board board){
        int boardScore=0;

        return boardScore;
    }


}



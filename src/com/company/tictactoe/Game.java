package com.company.tictactoe;

import java.util.List;
import java.util.Scanner;

public class Game {
    //int players;
    int humanPlayer=2;
    int firstPlayer;
    Board board = new Board();
    int[] cord = new int[2];
    Scanner input = new Scanner(System.in);
    String s;
    String[] cordString;

    public void Game(int firstPlayer){

        // this.players = players;
        //this.humanPlayer = humanPlayer;
        this.firstPlayer = firstPlayer;
        startGame(firstPlayer);

    }

    private void startGame(int player) {

        while (true) {
            board.printBoard();
            if (board.numberOfTokensOnTheBoard < 6) {
                if (player == humanPlayer) {
                    System.out.println("Enter x,y coordinate for putting a token on the board");
                    s = input.nextLine();
                    cordString = s.split(",");
                    cord[0] = Integer.parseInt(cordString[0]);
                    cord[1] = Integer.parseInt(cordString[1]);
                    int index = (cord[1] - 1) * 3 + cord[0] - 1;
                    board.setOwner(index, player);
                    board.setNumberOfTokensOnTheBoard(board.getNumberOfTokensOnTheBoard() + 1);
                    System.out.println(board.getNumberOfTokensOnTheBoard());
                    //get Coordinates for placing piece into cord. 0=x, 1=y
                }
            }else {
                if (player == humanPlayer) {
                    int[] cord = new int[4];
                    System.out.println("Enter fromX,fromY,toX,toY coordinate moving a token");
                    s = input.nextLine();
                    cordString = s.split(",");
                    cord[0] = Integer.parseInt(cordString[0]);
                    cord[1] = Integer.parseInt(cordString[1]);
                    cord[2] = Integer.parseInt(cordString[2]);
                    cord[3] = Integer.parseInt(cordString[3]);
                    int fromIndex = (cord[1] - 1) * 3 + cord[0] - 1;
                    int toIndex = (cord[3] - 1) * 3 + cord[2] - 1;
                    board.movePiece(fromIndex, toIndex);
                    //move piece from one field to another.
                }
            }
            Node node=new Node();
            Board AIMove;
            int alpha = -1000;
            int beta = 1000;
            boolean leaf=false;
            boolean maxiMini = true;
            int maxDepth = 10;
            int depth = 0;

            AIMove = node.alphaBetaExecute(board, alpha, beta, maxiMini, player, maxDepth, depth);
            board.recordAIMove(AIMove);
            //Do the AlphaBetaThing and get Coordinates for placing piece into cord. 0=x, 1=y
            player = getNewPlayer(player);
        }
    }




    private int getNewPlayer(int player){
        if (player == 1){
            return 2;
        }
        return 1;
    }
}



package com.company.tictactoe;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    int humanPlayer=2;
    int firstPlayer;
    Board board = new Board();
    int[] cord = new int[2];
    Scanner input = new Scanner(System.in);
    String s;
    String[] cordString;


    public void Game(int firstPlayer){
        this.firstPlayer = firstPlayer;
        startGame(firstPlayer);
    }

    private void startGame(int player) {
        while (true) {
            board.printBoard();
            if (board.getNumberOfTokensFromTheBoard() < 6) {
                if (player == humanPlayer) {
                    System.out.println("Enter x,y coordinate for putting a token on the board");
                    s = input.nextLine();
                    cordString = s.split(",");
                    cord[0] = Integer.parseInt(cordString[0]);
                    cord[1] = Integer.parseInt(cordString[1]);
                    int index = (cord[1] - 1) * 3 + cord[0] - 1;
                    board.setOwner(2, index);
                    //get Coordinates for placing piece into cord. 0=x, 1=y
                    board.printBoard();
                    player=getNewPlayer(player);
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
                    board.printBoard();
                    player=getNewPlayer(player);
                }
            }

            //AI Player
            Node rootNode=new Node();
            int alpha = -1000;
            int beta = 1000;
            int maxDepth = 2;
            int depth = 0;
            ArrayList<String> bestMove= new ArrayList<String>();
            Board AIboard = new Board();
            board.copyBoard(AIboard);
            int bestMoveAmount = rootNode.alphaBetaExecute(AIboard, alpha, beta, 1, maxDepth, depth, bestMove);
            printBestMove(bestMove);
            System.out.println("BestMove: "+bestMoveAmount);
            if (bestMove.size()!=0) {
                String[] splitMoveData = bestMove.remove(0).split(",");
                switch (splitMoveData[0]) {
                    case "put":
                        System.out.println("put");
                        board.setOwner(1, Integer.parseInt(splitMoveData[2]));
                        break;
                    case "move":
                        System.out.println("move");
                        board.movePiece(Integer.parseInt(splitMoveData[1]), Integer.parseInt(splitMoveData[2]));
                        break;
                }
                //board.setNumberOfTokens(numberOfTokens);
                player=getNewPlayer(player);
                board.calculateBoardValue(0);
                if (board.getValue(9)>100){
                    gameIsWon(player);
                }
            }
        }
    }

    private void gameIsWon(int player) {
        System.out.println("Spiller "+ player + " vandt spillet");
        System.exit(0);
    }

    private void printBestMove(ArrayList<String> bestMove) {
            String[] data = bestMove.get(0).split(",");
            System.out.println("(Command:"+data[0]+", Player:"+data[1]+", index:"+data[2]);
    }

    private int getNewPlayer(int player){
        if (player == 1){
            return 2;
        }
        return 1;
    }
}



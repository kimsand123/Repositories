package com.company.tictactoe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private int numberOfPiecesForPlayer = 0;
    private ArrayList<String> legalMoves = new ArrayList<>();

    public Node() {
    }

    public int alphaBetaExecute(Board board, int incomingAlpha, int incomingBeta, int player, int maxDepth, int depth) {
        int alpha = incomingAlpha;
        int beta = incomingBeta;

        if (board.getNumberOfTokensFromTheBoard()>=6) {
            numberOfPiecesForPlayer = 3;
        }
        //Hvis tilstand er et leaf
        if (maxDepth==depth) {
            return board.calculateBoardValue(depth);
        }
        legalMoves = getLegalMoves(board, player, numberOfPiecesForPlayer);
        //If it is a maximizer
        if (depth%2==0) {
            //Maximizer
            while (alpha < beta) {
                if (legalMoves.isEmpty()) {
                    break;
                } else {
                    //Tag en tilstand fra antallet af beregnede tilstande og alphaBeta den

                    Board newBoard = performMove(legalMoves.remove(0), board);
                    newBoard.calculateBoardValue(player);
                    int V = alphaBetaExecute(newBoard, alpha, beta, getNewPlayer(player), maxDepth, depth + 1);
                    if (V > alpha) {
                        alpha = V;
                    }
                }
            }
            return alpha;
        }
        //Minimizer
        while (alpha < beta) {
            if (legalMoves.isEmpty()) {
                break;
            } else {
                Board newBoard = performMove(legalMoves.remove(0), board);
                newBoard.calculateBoardValue(player);
                int V = alphaBetaExecute(board, alpha, beta, getNewPlayer(player), maxDepth, depth + 1);
                if (V<beta) {
                    beta = V;
                }
            }
        }
        return beta;
    }

    private Board performMove(String legalMove, Board board) {
        Board newBoard = new Board();
        newBoard.setBoardData(board.getBoardData());
        String[] splitMoveData;
        splitMoveData= legalMove.split(",");
        switch(splitMoveData[0]){
            case "put":
                newBoard.setOwner(Integer.parseInt(splitMoveData[1]),Integer.parseInt(splitMoveData[2]));
                break;
            case "move":
                newBoard.movePiece(Integer.parseInt(splitMoveData[1]), Integer.parseInt(splitMoveData[2]));
                break;
        }
        return newBoard;
    }

    public int getNewPlayer(int player){
        if (player == 1){
            return 2;
        } else{
            return 1;
        }
    }

    private ArrayList<String> getLegalMoves(Board board, int player, int numberOfPiecesForPlayer) {
        ArrayList<String> legalMoves = new ArrayList<>();
        int moveIndex=0;
        switch(numberOfPiecesForPlayer){
            //For case 1 og 2 tages en brik fra hånden og sættes
            //i et tomt felt
            case 0:
            case 1:
            //put,player,index
            case 2:
                for(int index = 0;index<9;index++){
                    if(board.getOwner(index)==0){
                        legalMoves.add("put,"+player+","+index);
                        //legalMoves[moveIndex]="put,"+player+","+index;
                        moveIndex++;
                    }
                }
                break;
            //For case 3 skal der fjernes en brik fra brættet
            //Som sættes ned i et tomt felt.
            //move,fromIndex,toIndex
            case 3:
                for (int index =0;index<9;index++){
                    if (board.getOwner(index)==player){
                        for (int newIndex = 0; newIndex<9;newIndex++){
                            if(board.getOwner(newIndex)==0){
                                legalMoves.add("move,"+moveIndex+","+newIndex);
                                //legalMoves[moveIndex]="move,"+moveIndex+","+newIndex;
                                moveIndex++;
                            }
                        }
                    }
                }
                break;
        }
        return legalMoves;
    }

    private Board copyBoard(Board board){
        Board boardCopy = new Board();
        for (int taller = 0;taller<9;taller++){
            boardCopy.setOwner(taller, board.getOwner(taller));
        }
        return boardCopy;
    }
}

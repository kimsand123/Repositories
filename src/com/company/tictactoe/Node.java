package com.company.tictactoe;

import java.util.ArrayList;

public class Node {

    public Node() {
    }

    public int alphaBetaExecute(Board board, int incomingAlpha, int incomingBeta, int player, int maxDepth, int depth, ArrayList<String> path) {
        int alpha = incomingAlpha;
        int beta = incomingBeta;
        int numberOfPlayingPieces=board.getNumberOfTokensFromTheBoard();

        //Hvis tilstand er et leaf
        if (maxDepth==depth) {
            return board.calculateBoardValue(depth);
        }

        //If it is a maximizer
        if (depth%2==0) {
            //Maximizer
            ArrayList<String> legalMoves = getLegalMoves(board, player, numberOfPlayingPieces);
            while (alpha < beta) {

                if (legalMoves.isEmpty()) {
                    break;
                } else {
                    //Tag en tilstand fra antallet af beregnede tilstande og alphaBeta den
                    String legalMove = legalMoves.remove(0);
                    Board newBoard = performMove(legalMove, board);
                    newBoard.calculateBoardValue(player);

                    int V = alphaBetaExecute(newBoard, alpha, beta, getNewPlayer(player), maxDepth, depth + 1, path);
                    if (V > alpha) {
                        path.add(legalMove);
                        alpha = V;
                        numberOfPlayingPieces = numberOfPlayingPieces +1;

                    }
                }
            }
            return alpha;
        }
        //Minimizer
        ArrayList<String> legalMoves = getLegalMoves(board, player, numberOfPlayingPieces);
        while (alpha < beta) {

            if (legalMoves.isEmpty()) {
                break;
            } else {
                String legalMove = legalMoves.remove(0);
                Board newBoard = performMove(legalMove, board);
                newBoard.calculateBoardValue(player);
                int V = alphaBetaExecute(newBoard, alpha, beta, getNewPlayer(player), maxDepth, depth + 1, path);
                if (V<beta) {
                    path.add(legalMove);
                    beta = V;
                    numberOfPlayingPieces = numberOfPlayingPieces +1;
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
        if (numberOfPiecesForPlayer<6) {
            for (int index = 0; index < 9; index++) {
                if (board.getOwner(index) == 0) {
                    legalMoves.add("put," + player + "," + index);

                }
            }
        } else{
            //flytte rundt på eksisterende brikker
            for (int index = 0;index<9;index++){
                if (board.getOwner(index)==player){
                    for (int newIndex = 0; newIndex<9;newIndex++){
                        if(board.getOwner(newIndex)==0){
                            legalMoves.add("move,"+index+","+newIndex);
                        }
                    }
                }
            }
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

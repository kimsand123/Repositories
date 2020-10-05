package com.company.tictactoe;

import java.util.ArrayList;

public class Node {

    public Node() {
    }

    public int alphaBetaExecute(Board board, int incomingAlpha, int incomingBeta, int player, int maxDepth, int depth, ArrayList<String> bestMove) {
        int alpha = incomingAlpha;
        int beta = incomingBeta;
        int numberOfPlayingPieces=board.getNumberOfTokensFromTheBoard();

        //Hvis tilstand er et leaf
        if (maxDepth==depth) {
            return board.calculateBoardValue(depth);
        }
        ArrayList<String> legalMoves = getLegalMoves(board, player, numberOfPlayingPieces);
        //If it is a maximizer
        if (depth%2==0) {
            //Maximizer


            while (alpha < beta) {
                if (legalMoves.isEmpty()) {
                    break;
                } else {
                    //Tag en tilstand fra antallet af beregnede tilstande og alphaBeta den
                    String legalMove = legalMoves.remove(0);
                    Board newBoard = performMove(legalMove, board);
                    //board.addNumberOfTokensToTheBoard();
                    newBoard.calculateBoardValue(player);
                    int V = alphaBetaExecute(newBoard, alpha, beta, getNewPlayer(player), maxDepth, depth + 1, bestMove);

                    if (V > alpha) {
                        if (depth == 0){
                            bestMove.clear();
                            bestMove.add(legalMove);
                        }
                        alpha = V;
                    }
                }
            }
            return alpha;
        }else {
            //Minimizer
            while (alpha < beta) {
                if (legalMoves.isEmpty()) {
                    break;
                } else {
                    String legalMove = legalMoves.remove(0);
                    Board newBoard = performMove(legalMove, board);
                    newBoard.calculateBoardValue(player);
                    int V = alphaBetaExecute(newBoard, alpha, beta, getNewPlayer(player), maxDepth, depth + 1, bestMove);
                    if (V < beta) {
                        /*if (depth == 0) {
                            bestMove.add(legalMove);
                        }*/
                        beta = V;
                    }
                }
            }
            return beta;
        }
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
        /*if(board.getNumberOfTokensFromTheBoard()<6) {
            newBoard.setNumberOfTokens(board.getNumberOfTokensFromTheBoard() + 1);
        }*/
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
        //Hvis der ikke er 6 brikker på brættet endnu så skal legal moves laves ved at putte en brik på brættet.
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

package com.company.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int maxDepth;
    private int depth;
    private List<Board> listOfChildren;
    private int alpha;
    private int beta;
    private Board board;
    private boolean leaf; //leaf = true, node = false
    private boolean maxiMini; //1=maximizer, 0=minimizer
    private int boardValue=0;
    private int numberOfPiecesForPlayer=0;
    private int player = 0;

    public Node(){
    }

    public Board alphaBetaExecute(Board board, int alpha, int beta, boolean maxiMini, int player, int maxDepth, int depth){
        this.alpha = alpha;
        this.beta = beta;
        this.maxiMini = maxiMini;
        this.maxDepth = maxDepth;
        this.depth = depth;
        this.board = board;
        this.player = player;

        if(depth>3){
            numberOfPiecesForPlayer = 3;
        }

        if (depth == maxDepth){
            return board;
        }

        ArrayList<Board> listOfBoards = getBoards(board, player, numberOfPiecesForPlayer);
        if (isMaxiMini()){
            //Maximizer
            while (this.alpha<this.beta) {
                if (listOfBoards.isEmpty()) {
                    break;
                }else{
                    Board childBoard = listOfBoards.remove(0);
                    Board V = alphaBetaExecute(childBoard, alpha, beta, nextMaxiMini(), getNewPlayer(player), maxDepth, depth + 1);
                    int staticValue = V.calculateBoardValue(player);
                    if (staticValue > this.alpha) {
                        this.alpha = staticValue;
                        this.board = V;
                    }
                }
            }
            return this.board;
        }
        //Minimizer
        while (this.alpha<this.beta) {
            if (listOfBoards.isEmpty()) {
                break;
            }else{
                Board childBoard = listOfBoards.remove(0);
                Board V = alphaBetaExecute(board, alpha, beta, nextMaxiMini(), getNewPlayer(player), maxDepth, depth + 1);
                int staticValue = V.calculateBoardValue(player);
                if (staticValue < this.beta) {
                    this.beta = staticValue;
                    this.board = V;
                }
            }
        }
        return this.board;
    }

    public int getNewPlayer(int player){
        if (player == 1){
            return 2;
        } else{
            return 1;
        }
    }

    private ArrayList<Board> getBoards(Board board, int player, int numberOfPiecesForPlayer) {
        ArrayList<Board> newListOfBoards = new ArrayList<>();
        Board tmpBoard = copyBoard(board);

        switch(numberOfPiecesForPlayer){
            //For case 1 og 2 tages en brik fra hånden og sættes
            //i et tomt felt
            case 0:
            case 1:

            case 2:
                for(int index = 0;index<9;index++){
                    if(tmpBoard.getOwner(index)==0){
                        tmpBoard.setOwner(index, player);
                        newListOfBoards.add(copyBoard(tmpBoard));
                        tmpBoard.setOwner(index,0);
                    }
                }
                break;
            //For case 3 skal der fjernes en brik fra brættet
            //Som sættes ned i et tomt felt.
            case 3:
                for (int index =0;index<9;index++){
                    if (tmpBoard.getOwner(index)==player){
                        for (int tmpIndex = 0; tmpIndex<9;tmpIndex++){
                            if(tmpBoard.getOwner(tmpIndex)==0){
                                tmpBoard.setOwner(tmpIndex,player);
                                tmpBoard.setOwner(index,0);
                                newListOfBoards.add(tmpBoard);
                                tmpBoard.setOwner(tmpIndex,0);
                                tmpBoard.setOwner(index,player);
                            }
                        }
                    }
                }
                break;
        }
        return newListOfBoards;
    }

    private Board copyBoard(Board board){
        Board boardCopy = new Board();
        for (int taller = 0;taller<9;taller++){
            boardCopy.setOwner(taller, board.getOwner(taller));
        }
        return boardCopy;
    }

    private boolean nextMaxiMini(){
        return !this.maxiMini;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /*public int getAlpha() {
        return alpha;
    }*/

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /*public int getBeta() {
        return beta;
    }*/

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    /*public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }*/

    public boolean isMaxiMini() {
        return maxiMini;
    }

   /* public void setMaxiMini(boolean maxiMini) {
        this.maxiMini = maxiMini;
    }*/
}

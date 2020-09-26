package com.company.tictactoe;

import java.util.List;

public class Node {

    private int maxDepth;
    private int depth;
    private List<Node> listOfChildren;
    private int alpha;
    private int beta;
    private Board board;
    private boolean leaf; //leaf = true, node = false
    private boolean maxiMini; //1=maximizer, 0=minimizer
    private int boardValue=0;

    public Node(List<Board> childList, int alpha, int beta, boolean leaf, boolean maxiMini, int maxDepth, int depth){
        this.board = board;
        this.alpha = alpha;
        this.beta = beta;
        this.leaf = leaf;
        this.maxiMini = maxiMini;
        this.maxDepth = maxDepth;
        this.depth = depth;
    }

    public int alphaBetaExecute(){
        if (isLeaf()){
            return staticValue(board);
        }
        List<Board> listOfBoards = getBoards(board);
        if (isMaxiMini()){
            while (this.alpha<this.beta) {
                int V =
                if(V>this.alpha){
                    this.alpha=V;
                }
            }
            return this.alpha;
        } else{

        }
    }

    public int staticValue(Board board){



        return this.boardValue;
    }

    private List<Board> getBoards(Board board, int player, int numberOfPiecesForPlayer) {
        List<Board> newListOfBoards = null;
        Board tmpBoard = copyBoard(board);

        switch(numberOfPiecesForPlayer){
                //For case 1 og 2 tages en brik fra hånden og sættes
                //i et tomt felt
            case 1:

            case 2:
                for(int index = 0;index<9;index++){
                    if(tmpBoard.getOwner(index)==0){
                        tmpBoard.setOwner(index, player);
                        newListOfBoards.add(tmpBoard);
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

    public Node getChild(int childNumber){
        if (childNumber>9 || childNumber < 0){
            return null;
        }
        if (nodeArray[childNumber]==null){
            return null;
        }
        return nodeArray[childNumber];
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

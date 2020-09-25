package com.company.tictactoe;

public class Node {

    Node[] nodeArray;
    int alpha;
    int beta;
    Board board;
    boolean leaf; //leaf = true, node = false
    int boardValue=0;

    public Node(Board board, int alpha, int beta, boolean leaf, int player){
        createNode(board, alpha, beta, leaf, player);
    }
    public void createNode(Board board, int alpha, int beta, boolean leaf, int player){
        if (!leaf){
            nodeArray = new Node[9];
        } else {
            this.boardValue = board.calculateBoardValue(board, player);
        }

        this.board = board;
        this.alpha = alpha;
        this.beta = beta;
        this.leaf = leaf;

    }

    public boolean addChild(Node node){
        int next = nodeArray.length + 1;
        if (next>9){
            return false;
        } else{
            nodeArray[next]= node;
        }
        return true;
    }

    public int getAmountOfChildren(){
        return nodeArray.length;
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

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

}

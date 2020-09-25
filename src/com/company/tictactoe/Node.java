package com.company.tictactoe;

public class Node {

    Node[] nodeArray;
    int alpha;
    int beta;
    Board board;
    boolean leaf; //leaf = true, node = false
    int boardValue=0;

    public Node(){

    }

    public void createNode(int alpha, int beta){
        nodeArray = new Node[9];
        this.alpha = alpha;
        this.beta = beta;
    }

    public void createLeaf(Board board, int player){
        setLeaf(true);
        this.board = board;
        this.boardValue = board.calculateBoardValue(board, player);
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

    public void addNode(Board board){

    }

}

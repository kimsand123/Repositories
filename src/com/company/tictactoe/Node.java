package com.company.tictactoe;

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

    public Node(List<Board> childList, int alpha, int beta, boolean leaf, boolean maxiMini, int player, int maxDepth, int depth){
        this.board = board;
        this.alpha = alpha;
        this.beta = beta;
        this.leaf = leaf;
        this.maxiMini = maxiMini;
        this.maxDepth = maxDepth;
        this.depth = depth;
        this.player = player;


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
            return staticValue(board);
        }

        List<Board> listOfBoards = getBoards(board, player, numberOfPiecesForPlayer);
        if (isMaxiMini()){
            while (this.alpha<this.beta|| !(listOfBoards.isEmpty())) {
                Board childBoard = listOfBoards.remove(1);
                Board V = alphaBetaExecute(childBoard,alpha,beta,!maxiMini,getNewPlayer(player),maxDepth,depth+1);
                if(V.getStaticValue()>this.alpha){
                    this.alpha=V.getStaticValue();
                    this.board = childBoard;
                }
            }
            return this.board;
        }

        while (this.alpha<this.beta||!(listOfBoards.isEmpty())) {
            Board childBoard = listOfBoards.remove(1);
            Board V = alphaBetaExecute(board, alpha, beta, !maxiMini, getNewPlayer(player), maxDepth, depth + 1);
            if (V.getStaticValue() < this.beta) {
                this.beta = V.getStaticValue();
                this.board = childBoard;
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

    public Board staticValue(Board board){



        return board;
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

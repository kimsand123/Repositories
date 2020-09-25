package com.company.tictactoe;

public class Field {
    private int value;
    private int owner;  //values 0=free, 1=player1, 2=player2

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}

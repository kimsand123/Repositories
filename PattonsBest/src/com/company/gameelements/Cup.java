package com.company.gameelements;

import java.util.Random;

public class Cup {
    private Random die = new Random();
    private int[] dice = new int[3];

    public int rollOne10Die(){
        return die.nextInt(10);
    }

    public int[] rollTwo10Dice(){
        dice[0] = die.nextInt(10);
        dice[1] = die.nextInt(10);
        dice[2] = dice[0] + dice[1];
        return dice;
    }
}

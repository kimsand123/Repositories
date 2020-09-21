package com.company.maze;

import java.util.Stack;

public class Coordinates {
    Stack<Coordinate> stack = new Stack<Coordinate>();

    public void push(Coordinate element){
        stack.push(element);
    }

    public Coordinate pop(){
        Coordinate element = new Coordinate();
        element = stack.pop();
        return element;
    }

    public int size(){
        return stack.size();
    }
}

package com.company;

import com.company.maze.Coordinate;
import com.company.maze.Coordinates;
import com.company.maze.Maze;

public class Recursive {
    //Lektion 1 Recursive training
    public int factorial (int n) {
        if (n <= 1) {
            return (1);
        }
        return (factorial(n - 1) * n);
    }

    public String simplify (int nominator, int denominator){
        int divisor = gcd(nominator, denominator);
        return nominator/divisor + "/" + denominator/divisor;
    }

    public int gcd(int a, int b){
        if (a > b){
            return gcd(a-b, b);
        }
        if (b > a){
            return gcd(a, b-a);
        }
        return a;
    }

    public int fibnoacci (int n) {
        if (n<3&&n>0){
            return 1;
        }
        if (n <= 0){
            return 0;
        }
        return fibnoacci(n-1)+fibnoacci(n-2);
    }

    public int power(int b, int e){
        if (e-1 < 1){
            return b;
        }
        return power(b,e-1)*b;
    }


    //Lektion 2 Maze

    //Game Initiation
    public void dfs_maze(Maze maze, int max_x){
        int index_of_start = maze.getStartPosition();
        int start_x = index_of_start%max_x-1;
        int start_y = (int)index_of_start/max_x;
        Coordinates coordinates = new Coordinates();
        maze_pathfinder(start_x, start_y, maze, coordinates);
        int numberOfElements = coordinates.size();
        Coordinate coordinate = new Coordinate();
        System.out.print("path=");
        for (int taller = 0;taller<numberOfElements;taller++){
            coordinate = coordinates.pop();
            System.out.print(convert_x_y_to_string_path(coordinate.xPos, coordinate.yPos));
        }
    }

    public boolean maze_pathfinder(int x, int y, Maze maze, Coordinates coordinates){
        if (maze.getPositionData(x,y)=='G'){
            coordinates.push(createCoordinateElement(x,y));
            //maze.setFinalRoute(x,y);
            return true;
        }

        maze.setVisited(x,y);
        int max_x = maze.getMax_x()-1;
        int max_y = maze.getMazeSize()/max_x-1;

        //check op
        if (y-1 >= 0 ){
            if (maze.getPositionData(x,y-1)==' ' && maze.isVisited(x,y-1)==false || maze.getPositionData(x,y-1)=='G'){
                if(maze_pathfinder(x, y-1, maze, coordinates)){
                    coordinates.push(createCoordinateElement(x,y));
                    //System.out.print(convert_x_y_to_string_path(x,y));
                    return true;
                }
            }
        }
        //check ned
        if (y+1 <= max_y){
            if (maze.getPositionData(x,y+1)==' ' && maze.isVisited(x, y+1)==false|| maze.getPositionData(x,y+1)=='G'){
                if(maze_pathfinder(x,y+1, maze, coordinates)) {
                    coordinates.push(createCoordinateElement(x,y));
                    //System.out.print(convert_x_y_to_string_path(x,y));
                    return true;
                }
            }
        }
        //check højre
        if (x+1 <= max_x){
            if (maze.getPositionData(x+1,y)==' ' && maze.isVisited(x+1, y)==false|| maze.getPositionData(x+1,y)=='G'){
                if(maze_pathfinder(x+1,y, maze, coordinates)){
                    coordinates.push(createCoordinateElement(x,y));
                    //System.out.print(convert_x_y_to_string_path(x,y));
                    return true;
                }
            }
        }
        //check venstre
        if (x-1 >= 0){
            if (maze.getPositionData(x-1,y)==' ' && maze.isVisited(x-1, y)==false|| maze.getPositionData(x-1,y)=='G'){
                if(maze_pathfinder(x-1,y, maze, coordinates)){
                    coordinates.push(createCoordinateElement(x,y));
                    //System.out.print(convert_x_y_to_string_path(x,y));
                    return true;
                }
            }
        }
        return false;
    }

    private Coordinate createCoordinateElement(int x, int y){
        Coordinate coordinate = new Coordinate();
        coordinate.xPos = x;
        coordinate.yPos = y;
        return coordinate;
    }

    public String convert_x_y_to_string_path(int x, int y){
        return ("("+ x + ","+ y +")");
    }
}
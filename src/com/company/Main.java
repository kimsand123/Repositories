package com.company;

import com.company.maze.Maze;
import com.company.tictactoe.Game;

public class Main {

    public static void main(String[] args) {
        // Lektion 1
        Recursive recurs = new Recursive();
        int n = 19;
        System.out.println("Factorial of " + n + " = " + recurs.factorial(n));
        System.out.println("The Fibonacci number "  + n + " is " + recurs.fibnoacci(n));
        int b = 10;
        int e = 5;
        System.out.println("Power of " + b + " in the " + e + " = " + recurs.power(b,e));
        int a = 17;
        int b2 = 170;
        System.out.println("The greatest common denominator of " + a + " and " + b2 + " is " + recurs.gcd(a, b2));
        int nominator = 8;
        int denominator = 12;
        System.out.println("The simplified fraction of "+ nominator+"/"+denominator+ " is " + recurs.simplify(nominator, denominator));

        // Lektion 2
        String maze_graphics =
                "*************" +
                "* * * *     *" +
                "* *   * *** *" +
                "*  S***     *" +
                "* *     *** *" +
                "* * *** *   *" +
                "* * *   *** *" +
                "* * *** * * *" +
                "*         *G*" +
                "*************";
        int max_x=13;
        Maze maze = new Maze(maze_graphics, max_x);

        maze.printMaze();
        recurs.dfs_maze(maze, max_x);
        System.out.println();
        maze.printMaze();


        //aStar maze

        String maze_graphics2 =
                "*************" +
                        "* * * *     *" +
                        "* *   * *** *" +
                        "*  S***     *" +
                        "* *     *** *" +
                        "* * *** *   *" +
                        "* * *   *** *" +
                        "* * *** * * *" +
                        "*         *G*" +
                        "*************";
        int max_x2=13;
        Maze maze2 = new Maze(maze_graphics2, max_x2);

        maze2.printMaze();
        Astar pathSeach = new Astar(maze2);

        //TicTacToe
        System.out.println();
        System.out.println();
        Game game = new Game();
        game.Game(1);

    }
}
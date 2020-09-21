package com.company.maze;

public class Maze{
    String maze_graphics;
    //String maze_visited;
    int max_x;

    public int getMax_x() {
        return max_x;
    }

    public int getMazeSize(){
        return maze_graphics.length();
    }

    public CoordinateAStar convertIndexToCoordinate(int index){

        CoordinateAStar coordinate = new CoordinateAStar();
        if(index == 0){
         coordinate.xPos= 0;
         coordinate.yPos= 0;
        } else {
            coordinate.xPos = index%max_x-1;
            coordinate.yPos = (int)index/max_x;
        }

        return coordinate;
    }

    public Maze(String maze_graphics, int max_x){
        this.maze_graphics = maze_graphics;
        this.max_x = max_x;

        //Create visited maze, and fill it with n for not visited.
        //maze_visited = "n".repeat(maze_graphics.length());
    }

    public char getPositionData(int x, int y){
        return maze_graphics.charAt(convertCoordinateToIndex(x,y));
    }

    public void setVisited(int x, int y){
        char[] char_array = maze_graphics.toCharArray();
        char_array[convertCoordinateToIndex(x,y)]='y';
        maze_graphics = new String(char_array);
    }

    public boolean isVisited(int x, int y){
        return maze_graphics.charAt(convertCoordinateToIndex(x, y)) == 'y';
    }

    public int getStartPosition(){
        return maze_graphics.indexOf("S")+1;
    }

    public int getGoalPosition(){
        return maze_graphics.indexOf("G")+1;
    }

    public void printMaze(){
        for (int y = 0; y<maze_graphics.length()/max_x;y++){
            for(int x = 0; x<max_x;x++){
                System.out.print(getPositionData(x,y));
            }
            System.out.println();
        }
    }

    public void setFinalRoute(CoordinateAStar coordinate){
        char[] char_array = maze_graphics.toCharArray();
        char_array[convertCoordinateToIndex(coordinate.xPos,coordinate.yPos)]='X';
        maze_graphics = new String(char_array);
    }

    public void removePathNoise(){

    }

    private int convertCoordinateToIndex(int x, int y)  {
        return y*max_x+x;
    }
}

package com.company;

import com.company.maze.CoordinateAStar;
import com.company.maze.Maze;
import java.util.Comparator;
import java.util.PriorityQueue;
import static java.lang.Math.abs;

//Heuristic: afstand er den absolutte delta.x + absolutte delta.y dvs. fugleflugts linje
//aStar med en krølle.

//Jeg har 2 datastrukturer til koordinater.
//pq holder alle koordinater der bliver undersøgt. Denne datastruktur sorteres stigende efter hvor stor F værdien er - pq -
//runningQueue der holder et udtræk af koordinater der har den samme laveste F værdi i pq. Denne datastruktur sorteres efter hvor langt et element er fra Goal.

//Det er koordinaterne i runningQueue der bliver kørt igennem og checket, og resultatet af checket bliver lagt i pq.
//Hvis 13 er den lavest F i pq, bliver de elementer med F=13 lagt i runningQueue og algoritmen undersøge alle de koordinater der ligger i runningQueue, inden der
//trækkes et nyt koordinatsæt fra pq som så igen er det lavest F værdisæt i køen. Hvis algoritmen finder et nyt koordinat med samme F værdi som der aktuelt ligger i runningQueue
//lægges det med det samme i runningQueue istedet for i pq så det kan blive processeret med det samme.

//Dette afstedkommer at hvis der er flere veje der har samme F værdi, så bliver alle vejene med den F værdi fundet og undersøgt. Det koordinat i runningQueue med kortest heurstisk vej
//til Goal, vil altid blive undersøgt først.

public class Astar {
    public Astar(Maze maze){
        CoordinateAStar startCoordinate, goalCoordinates;
        //Koordinater på start og slut
        startCoordinate = maze.convertIndexToCoordinate(maze.getStartPosition());
        goalCoordinates = maze.convertIndexToCoordinate(maze.getGoalPosition());
        //Start vejfinding.
        maze_pathfinder(maze, startCoordinate, goalCoordinates);
    }

    public void maze_pathfinder(Maze maze, CoordinateAStar startCoordinate, CoordinateAStar goalCoordinate){
        PriorityQueue<CoordinateAStar> allCoordinatesQueue = new PriorityQueue<>(new Comparator <CoordinateAStar>() {
            @Override
            public int compare(CoordinateAStar coordinate1, CoordinateAStar coordinate2) {
                //Den med mindst distance til goal poller først
                int distance1=heuristic(coordinate1, goalCoordinate);
                int distance2=heuristic(coordinate2, goalCoordinate);
                return (distance1-distance2);
                //return coordinate1.totalCost - coordinate2.totalCost;
            }
        });
        PriorityQueue<CoordinateAStar> runningQueue;
        CoordinateAStar actualCoordinate;

        startCoordinate.totalCost=heuristic(startCoordinate,goalCoordinate);
        allCoordinatesQueue.add(startCoordinate);

        StringBuilder outString= new StringBuilder();
        Boolean exit = false;
        int numberOfIterations=0;

        while (exit == false) {
            //Populer runningQueue fra allCoordinatesQueue
            runningQueue = populateRunningQueue(allCoordinatesQueue, goalCoordinate);
            //undersøg runningQueue
            while(runningQueue.size()>0 && exit==false){
                numberOfIterations = numberOfIterations + 1;
                //hent næste koordinat i runningQueue
                actualCoordinate = runningQueue.poll();
                //Hvis vi er i mål.
                if (maze.getPositionData(actualCoordinate.xPos, actualCoordinate.yPos) == 'G') {
                    exit = true;
                    outString.append(convert_x_y_to_string_path(actualCoordinate));
                    System.out.println(outString.toString());
                    System.out.println("TotalNumberOfIterations= "+numberOfIterations);
                    break;
                }
                //marker koordinat som besøgt.
                maze.setVisited(actualCoordinate.xPos, actualCoordinate.yPos);
                //check de omkringliggende koordinater og tilføj dem til enten allCoordinatesQueue eller runningQueue
                checkNextStep(actualCoordinate, goalCoordinate, maze, allCoordinatesQueue, runningQueue);
                //udskriv aktuelt step.
                outString.append(convert_x_y_to_string_path(actualCoordinate));
                outString.append("\n");
            }
        }
    }

    public int heuristic (CoordinateAStar coordinate, CoordinateAStar goalCoordinate){
        int h = abs(coordinate.xPos-goalCoordinate.xPos) + abs(coordinate.yPos- goalCoordinate.yPos);
        return h;
    }

    public void checkNextStep(CoordinateAStar coordinate, CoordinateAStar goalCoordinate, Maze maze, PriorityQueue<CoordinateAStar> pq, PriorityQueue<CoordinateAStar> runningQueue){
        //check up
        checkCoordinate(coordinate, goalCoordinate, pq, runningQueue, maze, 0,-1);
        // check down
        checkCoordinate(coordinate, goalCoordinate, pq, runningQueue, maze, 0,1);
        // check right
        checkCoordinate(coordinate, goalCoordinate, pq, runningQueue, maze, 1,0);
        // check left
        checkCoordinate(coordinate, goalCoordinate, pq, runningQueue, maze, -1,0);
    }

    public void checkCoordinate(CoordinateAStar coordinate, CoordinateAStar goalCoordinate, PriorityQueue<CoordinateAStar> allCoordinatesQueue, PriorityQueue<CoordinateAStar> runningQueue, Maze maze, int x, int y){
        CoordinateAStar tmpCoordinate = new CoordinateAStar();
        tmpCoordinate.xPos = coordinate.xPos + x;
        tmpCoordinate.yPos = coordinate.yPos + y;
        //Hvis koordinatet der kigges på allerede findes i allCoordinatesQueue eller det allerede er besøgt skal der ikke gøre noget.
        if (!(isCoordinateInQueue(tmpCoordinate, allCoordinatesQueue) || maze.isVisited(tmpCoordinate.xPos,tmpCoordinate.yPos))) {
            //hvis koordinatet der kigges på indeholder et ' ' eller er Goal koordinatet så tilføj det til en af de 2 datastrukturer.
            if (maze.getPositionData(tmpCoordinate.xPos, tmpCoordinate.yPos) == ' ' || maze.getPositionData(tmpCoordinate.xPos, tmpCoordinate.yPos) == 'G'){
                //For det undersøgte koordinat, inkrementer walkcost G
                tmpCoordinate.walkCost = coordinate.walkCost + 1;
                //og opdater totalcost F = G + H
                tmpCoordinate.totalCost = tmpCoordinate.walkCost + heuristic(tmpCoordinate, goalCoordinate);
                //hvis F værdien af det undersøgte koordinat ikke er den samme som F værdien af det koordinat vi står på skal det i allCoordinatesQueue
                //ellers skal det i runningQueue så det kan blive undersøgt sammen med sine ligesindede.
                if (tmpCoordinate.totalCost != coordinate.totalCost){
                    allCoordinatesQueue.add(tmpCoordinate);
                }else{
                    runningQueue.add(tmpCoordinate);
                }
            }
        }
    }

    public PriorityQueue<CoordinateAStar> populateRunningQueue(PriorityQueue<CoordinateAStar> pq, CoordinateAStar goalCoordinate){
        PriorityQueue<CoordinateAStar> runningQueue = new PriorityQueue<>(new Comparator <CoordinateAStar>() {
            @Override
            public int compare(CoordinateAStar coordinate1, CoordinateAStar coordinate2) {
                //runningQueue sorteres således at det felt der har den mindste distance til goal coordinate poller først, dvs.
                int distance1=heuristic(coordinate1, goalCoordinate);
                int distance2=heuristic(coordinate2, goalCoordinate);
                //den med mindst distance til goal coordinate poller først
                return (distance1-distance2);
                //return (coordinate1.totalCost-coordinate2.totalCost);
                //return (coordinate1.walkCost - coordinate2.walkCost);
            }
        });
        while (true){
            //for at undgå null exception hvis der ikke er noget i pq... start position.
            runningQueue.add(pq.poll());
            if (pq.size()==0){
                break;
            }
            if (runningQueue.peek().totalCost!=pq.peek().totalCost){
                break;
            }
        }
        return runningQueue;
    }

    public String convert_x_y_to_string_path(CoordinateAStar coordinate){
        return ("("+ coordinate.xPos + ","+ coordinate.yPos +") TotalCost="+coordinate.totalCost + " WalkCost="+coordinate.walkCost);
    }

    public boolean isCoordinateInQueue(CoordinateAStar coordinate, PriorityQueue<CoordinateAStar> allCoordinatesQueue){
        //undersøg om det sendte koordinat allerede findes i allCoordinatesQueue
        for (CoordinateAStar queueCoordinate: allCoordinatesQueue
        ) {
            if (queueCoordinate.xPos == coordinate.xPos){
                if (queueCoordinate.yPos == coordinate.yPos){
                    return true;
                }
            }
        }
        return false;
    }

}


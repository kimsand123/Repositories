package com.company;

import com.company.maze.CoordinateAStar;

import java.util.Comparator;

public class TravelCostComparator implements Comparator<CoordinateAStar> {

    public int compare(CoordinateAStar coordinateAStar, CoordinateAStar t1) {
        if (coordinateAStar.totalCost < t1.totalCost) {
            return 1;
        } else {
            if (coordinateAStar.totalCost > t1.totalCost) {
                return -1;
            }
        }
        return 0;
    }
}

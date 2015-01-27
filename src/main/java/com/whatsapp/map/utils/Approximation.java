package com.whatsapp.map.utils;

import com.whatsapp.map.beans.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 27.01.15.
 */
public class Approximation {

    /**
     * Reduce closely placed points that will collapse in zoom out
     * @param input
     * @param threshold - Threshold is a radius of the circle around one point
     * so that after zooming out this circle area could be displayed as a single point
     * @return
     */
    public static List<Point> reduceThresholdDestination(List<Point> input, float threshold){
        if (input.size() == 1){
            return input;
        }

        List<Point> reduced = new LinkedList<Point>();
        //Iterate through all elemenst in array
        for (int i = 0; i < input.size(); i++){

            //Collect points that might collapse
            List<Point> toCollapse = new LinkedList<Point>();

            //Take a base point to compare with
            Point base = input.get(i);

            //Iterate through points that are in the threshold area of the base one
            //If there are no points to collapse - only base will be added to the list
            while (i < input.size() && isInThreshold(base, input.get(i), threshold)){
                toCollapse.add(input.get(i++));
            }

            //Get the mean point of selected points to collapse
            reduced.add(getMean(toCollapse));

            //All points to collapse are collected. Next iteration will increase i
            //so we have to move pointer one step back
            i--;
        }
        return reduced;
    }

    /**
     * Detect if there are points located on the straight line
     * The line should be nearly straight
     * @param input
     * @return
     */
    public static List<Double> reduceStraightLines(List<Double> input){
        return null;
    }

    /**
     * Define if the neighbor point is in spherical range of the base one
     * @param bsase
     * @param neighbor
     * @param threshold
     * @return
     */
    private static  boolean isInThreshold(Point bsase, Point neighbor, float threshold){
        return distance(bsase, neighbor) <= threshold;
    }

    /**
     * Count the absolute distance between two points
     * @param from
     * @param to
     * @return
     */
    private static double distance(Point from, Point to){
        double triangleLength = Math.abs(from.getX() - to.getX());
        double triangleHeight = Math.abs(from.getY() - to.getY());
        return Math.sqrt(Math.pow(triangleHeight, 2) + Math.pow(triangleLength, 2));
    }

    /**
     * Get the mean point from selected list
     * @param points
     * @return
     */
    private static Point getMean(List<Point> points){
        if (points.size() == 1){
            return points.get(0);
        }
        double x = 0;
        double y = 0;
        for (Point point : points){
            x += point.getX();
            y += point.getY();
        }
        return new Point(x/points.size(), y/points.size());
    }
}

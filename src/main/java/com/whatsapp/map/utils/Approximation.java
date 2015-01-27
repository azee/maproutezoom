package com.whatsapp.map.utils;

import com.whatsapp.map.beans.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 27.01.15.
 * In real world it is better to use something like ApacheCommons Math;
 */
public class Approximation {

    public static List<Point> zoomOut(List<Point> input, double threshold){
        return interpolate(reduceThresholdDestination(input, threshold), threshold);
    }

    /**
     * Reduce closely placed points that will collapse while zoom out
     * @param input
     * @param threshold - Threshold is a radius of the circle around one point
     * so that after zooming out this circle area could be displayed as a single point
     * @return
     */
    public static List<Point> reduceThresholdDestination(List<Point> input, double threshold){
        if (input.size() == 1){
            return input;
        }

        List<Point> reduced = new LinkedList<Point>();
        //Iterate through all elements in the list
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
    public static List<Point> interpolate(List<Point> input, double threshold){
        if (input.size() <= 2){
            return input;
        }

        List<Point> reduced = new LinkedList<Point>();
        for (int i = 0; i < input.size(); i++){
            List<Point> toCollapse = new LinkedList<Point>();

            //Take a base point to compare with
            Point base = input.get(i);

            //Calculate current absolute angle sinus
            double sinus = 0;

            //Add to collapse collection if points lie on one same line with threshold tolerance
            while (i < input.size() &&
                    (isOnLine(base, input.get(i), sinus, threshold) ||
                            toCollapse.size() < 2)){
                sinus = getSinus(base, input.get(i), 0, 0);
                toCollapse.add(input.get(i++));
            }

            //Reduce the list by selecting only extreme points
            reduced.addAll(getExtremes(toCollapse));

            //All points to collapse are collected. Next iteration will increase i
            //so we have to move pointer one step back
            i--;
        }
        return reduced;

    }

    /**
     * Define if the neighbor point is in spherical range of the base one
     * @param bsase
     * @param neighbor
     * @param threshold
     * @return
     */
    private static  boolean isInThreshold(Point bsase, Point neighbor, Double threshold){
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


    /**
     * If points are on the same line with threshold tolerance
     * @param base
     * @param neighbour
     * @param sinus
     * @param threshold
     * @return
     */
    private static boolean isOnLine(Point base, Point neighbour, double sinus, Double threshold){
        double maxSinus = getSinus(base, neighbour, 0, threshold);
        double minSinus = getSinus(base, neighbour, threshold, 0);
        return sinus >= minSinus && sinus <= maxSinus;
    }

    /**
     * Get the sinus value of an angle
     * @param one
     * @param two
     * @param xThreshold
     * @param yThreshold
     * @return
     */
    private static double getSinus(Point one, Point two, double xThreshold, double yThreshold){
        double xGradient = Math.abs(two.getX() - one.getX()) + xThreshold;
        double yGradient = Math.abs(two.getY() - one.getY()) + yThreshold;
        double hypotenuse = Math.sqrt(Math.pow(xGradient, 2) + Math.pow(yGradient, 2));
        return hypotenuse == 0 ? 0 : yGradient/hypotenuse;
    }

    /**
     * Get only extreme points from the list - reduce internal
     * @param input
     * @return
     */
    private static List<Point> getExtremes(List<Point> input){
        if (input.size() <= 2){
            return input;
        }
        List<Point> result = new LinkedList<Point>();
        result.add(input.get(0));
        result.add(input.get(input.size() - 1));
        return result;
    }
}

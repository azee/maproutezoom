package com.whatsapp.map.utils;

import com.whatsapp.map.beans.Point;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 27.01.15.
 */
public class ApproximationTest {
    @Test
    public void reduceThresholdDestinationAllInTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(1, 1));
        input.add(new Point(4, 5));
        input.add(new Point(7, 9));
        input.add(new Point(10, 12.33));

        List<Point> result = Approximation.reduceThresholdDestination(input, 1);
        assertThat(result, new PointListMatcher(input));
    }

    @Test
    public void reduceThresholdDestinationTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(1, 1));
        input.add(new Point(2, 2));
        input.add(new Point(3, 2));
        input.add(new Point(4, 3));
        input.add(new Point(7, 7));
        input.add(new Point(8, 8));
        input.add(new Point(15, 15));

        List<Point> expexted = new LinkedList<Point>();
        expexted.add(new Point(2.5, 2));
        expexted.add(new Point(7.5, 7.5));
        expexted.add(new Point(15, 15));

        List<Point> result = Approximation.reduceThresholdDestination(input, 6);
        assertThat(result, new PointListMatcher(expexted));
    }

    @Test
    public void reduceThresholdDestinationEmptyListTest(){
        List<Point> input = new LinkedList<Point>();
        List<Point> result = Approximation.reduceThresholdDestination(input, 1);
        assertThat(result, new PointListMatcher(new ArrayList<Point>()));
    }

    @Test
    public void reduceThresholdDestinationZeroTHTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(1, 1));
        input.add(new Point(2, 2));
        input.add(new Point(3, 2));

        List<Point> result = Approximation.reduceThresholdDestination(input, 0);
        assertThat(result, new PointListMatcher(input));
    }

}

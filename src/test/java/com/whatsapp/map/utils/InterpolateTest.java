package com.whatsapp.map.utils;

import com.whatsapp.map.beans.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by azee on 27.01.15.
 */
public class InterpolateTest {
    @Test
    public void interpolateOneLineTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(1, 1));
        input.add(new Point(2, 2));
        input.add(new Point(3, 3));
        input.add(new Point(5, 7));

        List<Point> expexted = new LinkedList<Point>();
        expexted.add(new Point(1, 1));
        expexted.add(new Point(3, 3));
        expexted.add(new Point(5, 7));

        List<Point> result = Approximation.interpolate(input, 1);
        assertThat(result, new PointListMatcher(expexted));
    }

    @Test
    public void interpolateOneLineXTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(1, 0));
        input.add(new Point(2, 0));
        input.add(new Point(3, 0));
        input.add(new Point(4, 1));
        input.add(new Point(4, 2));
        input.add(new Point(4, 3));

        List<Point> expexted = new LinkedList<Point>();
        expexted.add(new Point(1, 0));
        expexted.add(new Point(3, 0));
        expexted.add(new Point(4, 1));
        expexted.add(new Point(4, 3));

        List<Point> result = Approximation.interpolate(input, 1);
        assertThat(result, new PointListMatcher(expexted));
    }

    @Test
    public void interpolateOneLineYTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(0, 1));
        input.add(new Point(0, 2));
        input.add(new Point(0, 3));
        input.add(new Point(1, 1));
        input.add(new Point(1, 2));
        input.add(new Point(1, 3));

        List<Point> expexted = new LinkedList<Point>();
        expexted.add(new Point(0, 1));
        expexted.add(new Point(0, 3));
        expexted.add(new Point(1, 1));
        expexted.add(new Point(1, 3));

        List<Point> result = Approximation.interpolate(input, 1);
        assertThat(result, new PointListMatcher(expexted));
    }



}

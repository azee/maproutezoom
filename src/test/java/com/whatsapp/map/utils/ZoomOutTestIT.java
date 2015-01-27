package com.whatsapp.map.utils;

import com.whatsapp.map.beans.Point;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by azee on 27.01.15.
 */
public class ZoomOutTestIT {

    @Test
    public void zoomOutTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(1, 1));
        input.add(new Point(1.3, 1.5));
        input.add(new Point(3, 3));
        input.add(new Point(5.5, 5.5));
        input.add(new Point(6, 5.9));
        input.add(new Point(9, 12));
        input.add(new Point(11, 17));
        input.add(new Point(15, 27));
        input.add(new Point(17, 32));

        List<Point> expexted = new LinkedList<Point>();
        expexted.add(new Point(1.15, 1.25));
        expexted.add(new Point(5.75, 5.7));
        expexted.add(new Point(9, 12));
        expexted.add(new Point(17, 32));

        List<Point> result = Approximation.zoomOut(input, 1);
        assertThat(result, new PointListMatcher(expexted));
    }

    @Test
    public void zoomOutReversePointsTest(){
        List<Point> input = new LinkedList<Point>();
        input.add(new Point(17, 32));
        input.add(new Point(15, 27));
        input.add(new Point(11, 17));
        input.add(new Point(9, 12));
        input.add(new Point(6, 5.9));
        input.add(new Point(5.5, 5.5));
        input.add(new Point(3, 3));
        input.add(new Point(1.3, 1.5));
        input.add(new Point(1, 1));

        List<Point> expexted = new LinkedList<Point>();
        expexted.add(new Point(17, 32));
        expexted.add(new Point(9, 12));
        expexted.add(new Point(5.75, 5.7));
        expexted.add(new Point(1.15, 1.25));

        List<Point> result = Approximation.zoomOut(input, 1);
        assertThat(result, new PointListMatcher(expexted));
    }
}

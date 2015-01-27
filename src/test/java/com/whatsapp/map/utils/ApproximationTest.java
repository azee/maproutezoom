package com.whatsapp.map.utils;

import ch.lambdaj.Lambda;
import com.whatsapp.map.beans.Point;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

        assertThat(result.size(), is(4));

        assertThat(Lambda.extract(input, Lambda.on(Point.class).getX()),
                (Matcher) contains(
                        Lambda.extract(result, Lambda.on(Point.class).getX())
                )
        );

        assertThat(Lambda.extract(input, Lambda.on(Point.class).getY()),
                (Matcher) contains(
                        Lambda.extract(result, Lambda.on(Point.class).getY())
                )
        );
    }

}

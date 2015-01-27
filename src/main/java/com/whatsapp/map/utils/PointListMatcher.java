package com.whatsapp.map.utils;

import com.whatsapp.map.beans.Point;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;

/**
 * Created by azee on 27.01.15.
 */
public class PointListMatcher extends BaseMatcher {
    List<Point> points;

    PointListMatcher(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean matches(Object o) {
        List list = (List) (o);
        if (points.size() != list.size()){
            return false;
        }
        for (int i = 0; i < list.size(); i++){
            if (!points.get(i).equals(list.get(i))){
               return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(points);
    }
}

package com.whatsapp.map.beans;

/**
 * Created by azee on 27.01.15.
 */
public class Point {
    private double x;
    private double y;
    private boolean mandatory;

    public Point(double x, double y, boolean mandatory) {
        this.x = x;
        this.y = y;
        this.mandatory = mandatory;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Point))return false;
        Point other = (Point) obj;
        return this.getX() == other.getX() &&  this.getY() == other.getY();
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", mandatory=" + mandatory +
                '}';
    }
}

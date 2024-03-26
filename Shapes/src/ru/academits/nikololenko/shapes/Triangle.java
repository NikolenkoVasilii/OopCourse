package ru.academits.nikololenko.shapes;

import java.util.Arrays;

public class Triangle implements Shapes {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getX3() {
        return x3;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getY3() {
        return y3;
    }

    public static double getSideLength(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    private double getSide1() {
        return getSideLength(x1, x2, y1, y2);
    }

    private double getSide2() {
        return getSideLength(x2, x3, y2, y3);
    }

    private double getSide3() {
        return getSideLength(x3, x1, y3, y1);
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double epsilon = 1.0e-10;
        if (Math.abs((y3 - y1) * (x2 - x1) - (x3 - x1) * (y2 - y1)) <= epsilon) {
            return 0;
        }

        double halfPerimeter = getPerimeter() / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - getSide1()) *
                (halfPerimeter - getSide2()) * (halfPerimeter - getSide3()));
    }

    @Override
    public double getPerimeter() {
        return getSide1() + getSide2() + getSide3();
    }

    @Override
    public String toString() {
        double[] coordinates = {x1, y1, x2, y2, x3, y3};
        return Arrays.toString(coordinates);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2 && x3 == triangle.x3 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1) + Double.hashCode(y1) + Double.hashCode(x2) + Double.hashCode(y2) +
                Double.hashCode(x3) + Double.hashCode(y3);
        return hash;
    }
}
package ru.academits.nikololenko.shapes;

public class Rectangle implements Shapes {
    private final double length1;
    private final double length2;

    public Rectangle(double length1, double length2) {
        this.length1 = length1;
        this.length2 = length2;
    }

    @Override
    public double getWidth() {
        return Math.min(length1,length2);
    }

    @Override
    public double getHeight() {
        return Math.max(length1,length2);
    }

    @Override
    public double getArea() {
        return length1 * length2;
    }

    @Override
    public double getPerimeter() {
        return (length1 + length2) * 2;
    }

    @Override
    public String toString() {
        return "прямоугольник: " +
                "длина = " + this.getWidth() + "," + "высота = " + this.getHeight() + "," + "площадь = " + this.getArea() + "," + "периметр = " + this.getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Rectangle p = (Rectangle) o;
        return length1 == p.length1 && length2 == p.length2 || length1 ==  p.length2 && length2 == p.length1 ;
    }
    @Override
    public int hashCode() {
        final int prime = 37;
        double hash = 1;
        hash = prime * hash + length1 +  prime * hash + length2;
        return (int)hash;
    }
}
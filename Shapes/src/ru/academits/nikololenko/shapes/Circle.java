package ru.academits.nikololenko.shapes;

public class Circle implements Shapes {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;

    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "круг: " +
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
        Circle p = (Circle) o;
        return radius == p.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        double hash = 1;
        hash = prime * hash + radius;
        return (int)hash;
    }
}
package ru.academits.nikololenko.shapes;

public class Square implements Shapes {
    private final double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public double getWidth() {
        return length;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return length * length;
    }

    @Override
    public double getPerimeter() {
        return length * 4;
    }

    @Override
    public String toString() {
        return "квадрат: " +
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
        Square p = (Square) o;
        return length == p.length;
    }
    @Override
    public int hashCode() {
        final int prime = 37;
        double hash = 1;
        hash = prime * hash + length;
        return (int)hash;
    }
}
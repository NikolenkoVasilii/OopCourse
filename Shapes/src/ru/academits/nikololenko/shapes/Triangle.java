package ru.academits.nikololenko.shapes;

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
            return ZERO;
        } else {
            double triangleSide1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            double triangleSide2Length = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
            double triangleSide3Length = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
            double halfPerimeter = (triangleSide1Length + triangleSide2Length + triangleSide3Length) / 2;

            return Math.sqrt(halfPerimeter * (halfPerimeter - triangleSide1Length) *
                    (halfPerimeter - triangleSide2Length) * (halfPerimeter - triangleSide3Length));
        }
    }

    @Override
    public double getPerimeter() {
        double triangleSide1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double triangleSide2Length = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double triangleSide3Length = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        return triangleSide1Length + triangleSide2Length + triangleSide3Length;
    }

    @Override
    public String toString() {
        return "треугольник: " +
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
        double triangleSide1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double triangleSide2Length = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double triangleSide3Length = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        Triangle p = (Triangle) o;
        return x1 == p.x1 && y1 == p.y1 && x2 == p.x2 && y2 == p.y2 && x3 == p.x3 && y3 == p.y3;
    }
    @Override
    public int hashCode() {
        final int prime = 37;
        double hash = 1;
        hash = prime * hash + x1 +  prime * hash + y1 +  prime * hash + x2 +  prime * hash + y2 +
                prime * hash + x3 +  prime * hash + y3;
        return (int)hash;
    }
}
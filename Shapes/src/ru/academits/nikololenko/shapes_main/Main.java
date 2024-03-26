package ru.academits.nikololenko.shapes_main;

import ru.academits.nikololenko.shapes.*;

import java.util.Arrays;

public class Main {
    private static Shapes getShapeWithMaxArea(Shapes... shapes) {
        Arrays.sort(shapes, new ShapeComparatorArea());
        return shapes[shapes.length - 1];
    }

    private static Shapes getShapeWithSecondPerimeter(Shapes... shapes) {
        Arrays.sort(shapes, new ShapeComparatorPerimeter());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Square square1 = new Square(4);
        System.out.println("Длина стороны квадрата 1: " + square1.getSideLength() + "\n");

        Square square2 = new Square(3);
        System.out.println("Длина стороны квадрата 2: " + square1.getSideLength() + "\n");

        Rectangle rectangle1 = new Rectangle(3, 4);
        System.out.println("Ширина прямоугольника 1: " + rectangle1.getWidth());
        System.out.println("Высота прямоугольника 1: " + rectangle1.getHeight() + "\n");

        Rectangle rectangle2 = new Rectangle(4, 3);
        System.out.println("Ширина прямоугольника 2: " + rectangle1.getWidth());
        System.out.println("Высота прямоугольника 2: " + rectangle1.getHeight() + "\n");

        Triangle triangle1 = new Triangle(1, 2, 3, 4, 6, 8);
        System.out.println("x1 треугольника 1: " + triangle1.getX1());
        System.out.println("y1 треугольника 1: " + triangle1.getY1());
        System.out.println("x2 треугольника 1: " + triangle1.getX2());
        System.out.println("y2 треугольника 1: " + triangle1.getY2());
        System.out.println("x3 треугольника 1: " + triangle1.getX3());
        System.out.println("y3 треугольника 1: " + triangle1.getY3() + "\n");

        Triangle triangle2 = new Triangle(2, 2, 4, 4, 5, 5);
        System.out.println("x1 треугольника 2: " + triangle2.getX1());
        System.out.println("y1 треугольника 2: " + triangle2.getY1());
        System.out.println("x2 треугольника 2: " + triangle2.getX2());
        System.out.println("y2 треугольника 2: " + triangle2.getY2());
        System.out.println("x3 треугольника 2: " + triangle2.getX3());
        System.out.println("y3 треугольника 2: " + triangle2.getY3() + "\n");

        Circle circle1 = new Circle(5);
        System.out.println("Радиус круга 1: " + circle1.getRadius() + "\n");

        Circle circle2 = new Circle(10);
        System.out.println("Радиус круга 2: " + circle2.getRadius() + "\n");

        final Shapes[] shapes = {square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2};
        for (Shapes shape : shapes) {
            System.out.println("Класс фигуры: " + shape.getClass() + "\n" + "Поля фигуры" + shape);
            System.out.println("Площадь фигуры: " + shape.getArea());
            System.out.println("Периметр фигуры: " + shape.getPerimeter() + "\n");
        }

        if (rectangle1.equals(rectangle2)) {
            System.out.println("Прямоугольники 1 и 2 равны \n");
        } else {
            System.out.println("Прямоугольники 1 и 2 не равны \n");
        }

        Shapes maxShape = getShapeWithMaxArea(square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2);
        System.out.println("Класс максимальной фигуры: " + maxShape.getClass());
        System.out.println("Площадь максимальной фигуры = " + maxShape.getArea());
        System.out.println("Ширина = " + maxShape.getWidth());
        System.out.println("Высота = " + maxShape.getHeight());
        System.out.println("Периметр = " + maxShape.getPerimeter() + "\n");

        Shapes secondShape = getShapeWithSecondPerimeter(square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2);
        System.out.println("Класс фигуры со второй пофиличине площадью: " + maxShape.getClass());
        System.out.println("Площадь второй по величине фигуры = " + secondShape.getArea());
        System.out.println("Ширина = " + secondShape.getWidth());
        System.out.println("Высота = " + secondShape.getHeight());
        System.out.println("Периметр = " + secondShape.getPerimeter());
    }
}
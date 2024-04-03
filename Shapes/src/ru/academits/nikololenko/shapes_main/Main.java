package ru.academits.nikololenko.shapes_main;

import ru.academits.nikololenko.shapes.*;
import ru.academits.nikololenko.shapes_comparator.ShapeAreaComparator;
import ru.academits.nikololenko.shapes_comparator.ShapePerimeterComparator;

import java.util.Arrays;

public class Main {
    private static Shapes getShapeWithMaxArea(Shapes... shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shapes getShapeWithSecondPerimeter(Shapes... shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Square square1 = new Square(4);
        System.out.println(square1);

        Square square2 = new Square(3);
        System.out.println(square2);

        Rectangle rectangle1 = new Rectangle(3, 4);
        System.out.println(rectangle1);

        Rectangle rectangle2 = new Rectangle(4, 3);
        System.out.println(rectangle2);

        Triangle triangle1 = new Triangle(1, 2, 3, 4, 6, 8);
        System.out.println(triangle1);

        Triangle triangle2 = new Triangle(2, 2, 4, 4, 5, 5);
        System.out.println(triangle2);

        Circle circle1 = new Circle(5);
        System.out.println(circle1);

        Circle circle2 = new Circle(10);
        System.out.println(circle2);

        if (rectangle1.equals(rectangle2)) {
            System.out.println("Прямоугольники 1 и 2 равны \n");
        } else {
            System.out.println("Прямоугольники 1 и 2 не равны \n");
        }

        Shapes maxShape = getShapeWithMaxArea(square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2);
        if (maxShape == null) {
            System.out.println("Задан пустой массив фигур");
        } else {
            System.out.println(maxShape);
            System.out.println("Площадь максимальной фигуры = " + maxShape.getArea());
            System.out.println("Периметр = " + maxShape.getPerimeter() + "\n");
        }

        Shapes secondShape = getShapeWithSecondPerimeter(square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2);
        if (secondShape == null) {
            System.out.println("Задан пустой массив фигур");
        } else {
            System.out.println(maxShape);
            System.out.println("Площадь второй по величине фигуры = " + secondShape.getArea());
            System.out.println("Периметр = " + secondShape.getPerimeter());
        }
    }
}
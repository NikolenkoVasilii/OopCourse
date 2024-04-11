package ru.academits.nikololenko.shapes_main;

import ru.academits.nikololenko.shapes.*;
import ru.academits.nikololenko.shapes_comparators.ShapeAreaComparator;
import ru.academits.nikololenko.shapes_comparators.ShapePerimeterComparator;

import java.util.Arrays;

public class Main {
    private static Shape getShapeWithMaxArea(Shape... shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        if (shapes.length == 0 || shapes.length == 1) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(4),
                new Square(3),
                new Rectangle(3, 4),
                new Rectangle(4, 3),
                new Triangle(1, 2, 3, 4, 6, 8),
                new Triangle(2, 2, 4, 4, 5, 5),
                new Circle(5),
                new Circle(10)
        };

        for (Shape shape : shapes) {
            System.out.println(shape.toString());
        }

        System.out.println();

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);

        if (shapeWithMaxArea == null) {
            System.out.println("Задан пустой массив фигур");
        } else {
            System.out.println("Фигура с наибольшей площадью: " + shapeWithMaxArea);
            System.out.println("Площадь этой фигуры = " + shapeWithMaxArea.getArea());
            System.out.println("Периметр этой фигуры = " + shapeWithMaxArea.getPerimeter() + System.lineSeparator());
        }

        Shape shapeWithSecondPerimeter = getShapeWithSecondPerimeter(shapes);

        if (shapeWithSecondPerimeter == null) {
            System.out.println("Задан пустой массив фигур");
        } else {
            System.out.println("Фигура со вторым по величине периметром: " + shapeWithSecondPerimeter);
            System.out.println("Площадь этой фигуры = " + shapeWithSecondPerimeter.getArea());
            System.out.println("Периметр этой фигуры = " + shapeWithSecondPerimeter.getPerimeter());
        }
    }
}
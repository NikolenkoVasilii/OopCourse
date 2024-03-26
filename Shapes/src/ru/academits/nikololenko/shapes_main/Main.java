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
        Square square2 = new Square(3);
        Rectangle rectangle1 = new Rectangle(3, 4);
        Rectangle rectangle2 = new Rectangle(4, 3);
        Triangle triangle1 = new Triangle(1, 2, 3, 4, 6, 8);
        Triangle triangle2 = new Triangle(2, 2, 4, 4, 5, 5);
        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(10);

        final Shapes[] shapes = {square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2};
        for (Shapes shape : shapes) {
            System.out.println("Класс фигуры: " + shape.getClass() + "\n" + "Поля фигуры" + shape);
            System.out.println("Площадь фигуры: " + shape.getArea());
            System.out.println("Периметр фигуры: " + shape.getPerimeter() + "\n");
        }

        if (rectangle1.equals(rectangle2)) {
            System.out.println("Прямоугольники 1 и 2 равны");
        } else {
            System.out.println("Прямоугольники 1 и 2 не равны");
        }

        Shapes maxShape = getShapeWithMaxArea(square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2);
        System.out.println("Класс максимальной фигуры: " + maxShape.getClass());
        System.out.println("Площадь максимальной фигуры = " + maxShape.getArea());
        System.out.println("Ширина = " + maxShape.getWidth());
        System.out.println("Высота = " + maxShape.getHeight());
        System.out.println("Периметр = " + maxShape.getPerimeter());

        Shapes secondShape = getShapeWithSecondPerimeter(square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2);
        System.out.println("Класс фигуры со второй пофиличине площадью: " + maxShape.getClass());
        System.out.println("Площадь второй по величине фигуры = " + secondShape.getArea());
        System.out.println("Ширина = " + secondShape.getWidth());
        System.out.println("Высота = " + secondShape.getHeight());
        System.out.println("Периметр = " + secondShape.getPerimeter());
    }
}
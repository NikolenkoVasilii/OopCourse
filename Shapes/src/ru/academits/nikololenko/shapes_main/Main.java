package ru.academits.nikololenko.shapes_main;

import ru.academits.nikololenko.shapes.*;

import java.util.Arrays;


public class Main {

    public static void areaSorting(Shapes[] arrayShapes) {
        Arrays.sort(arrayShapes, (first, second) -> {
            if (first.getArea() != second.getArea()) {
                return (int) (second.getArea() - first.getArea());
            }
            return 0;
        });
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
        areaSorting(shapes);
        System.out.println(Arrays.toString(shapes));

        Shapes firstShape = shapes[0];
        Shapes secondShape = shapes[1];

        System.out.println("фигура с максимальной площадью - " + firstShape);
        System.out.println("фигура со второй площадью - " + secondShape);

        if (rectangle1.equals(rectangle2)) {
            System.out.println("Прямоугольники равны");
        } else {
            System.out.println("Прямоугольники не равны");
        }


    }

}
package ru.academits.nikololenko.shapes_comparators;

import java.util.Comparator;

import ru.academits.nikololenko.shapes.Shape;

public class ShapePerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}
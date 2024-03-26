package ru.academits.nikololenko.shapes_main;

import ru.academits.nikololenko.shapes.Shapes;

import java.util.Comparator;

public class ShapeComparatorArea implements Comparator<Shapes> {
    public int compare(Shapes shape1, Shapes shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
package ru.academits.nikololenko.shapes_comparator;

import ru.academits.nikololenko.shapes.Shapes;

import java.util.Comparator;


public class ShapeAreaComparator implements Comparator<Shapes> {
    @Override
    public int compare(Shapes shape1, Shapes shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
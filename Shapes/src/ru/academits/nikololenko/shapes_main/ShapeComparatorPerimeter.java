package ru.academits.nikololenko.shapes_main;

import java.util.Comparator;

import ru.academits.nikololenko.shapes.Shapes;

public class ShapeComparatorPerimeter implements Comparator<Shapes> {
    public int compare(Shapes shape1, Shapes shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}
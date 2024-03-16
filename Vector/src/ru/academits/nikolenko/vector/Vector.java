package ru.academits.nikolenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        int n = vector.getSize();
        coordinates = Arrays.copyOf(vector.coordinates, n);
    }

    public Vector(double[] coordinates) {
        int n = coordinates.length;
        if (n == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public Vector(int n, double[] coordinates) {
        if (n <= 0 || coordinates.length == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = getSize();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(coordinates[i]).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1).append("}");
        return stringBuilder.toString();
    }

    public void addVectors(Vector vector) {
        int size = this.getSize();
        int vectorSize = vector.getSize();
        if (size < vectorSize) {
            this.coordinates = Arrays.copyOf(coordinates, vectorSize);
        } else if (size > vectorSize) {
            vector.coordinates = Arrays.copyOf(coordinates, size);
        }
        for (int i = 0; i < Math.max(size, vectorSize); i++) {
            this.coordinates[i] += vector.coordinates[i];
        }
    }
    public void subtractVectors(Vector vector) {
        int size = this.getSize();
        int vectorSize = vector.getSize();
        if (size < vectorSize) {
            this.coordinates = Arrays.copyOf(coordinates, vectorSize);
        } else if (size > vectorSize) {
            vector.coordinates = Arrays.copyOf(coordinates, size);
        }
        for (int i = 0; i < Math.max(size, vectorSize); i++) {
            this.coordinates[i] -= vector.coordinates[i];
        }
    }
    public void scalarMultiplication( double scalar) {
        int size = this.getSize();
        for (int i = 0; i < size; i++) {
            this.coordinates[i] *= scalar;
        }
    }

    public void reversVector() {
        int size = this.getSize();
        for (int i = 0; i < size; i++) {
            this.coordinates[i] *= -1;
        }
    }

    public double length() {
        double support = 0;
        for (double element : this.coordinates) {
            support += Math.pow(element, 2);
        }
        return Math.sqrt(support);
    }

    public int getCoordinate(int number) {
        return (int) coordinates[number];
    }

    public void setCoordinate(int coordinate, int number) {
        this.coordinates[number] = coordinate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Vector p = (Vector) o;
        if (this.getSize() != p.getSize()) {
            return false;
        }
        int size = coordinates.length;
        for (int i=0; i<size; i++){
            if (this.coordinates[i] != p.coordinates[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        double hash = 1;
        for (double coordinate : coordinates) {
            hash += prime * hash + coordinate;
        }
        return (int)hash;
    }

    public static Vector addVectors (Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.addVectors(vector2);
        return vector;
    }

    public static Vector subtractVectors (Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtractVectors(vector2);
        return vector;
    }

    public static double scalarMultiplicationVectors (Vector vector1, Vector vector2) {
        double multiplication = 0;
        int size = Math.min(vector1.getSize(), vector2.getSize());
        for (int i = 0; i < size; i++){
            multiplication += vector1.coordinates[i] * vector2.coordinates[i];
        }
        return  multiplication;
    }
}

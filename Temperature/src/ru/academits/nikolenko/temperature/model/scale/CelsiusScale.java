package ru.academits.nikolenko.temperature.model.scale;

public class CelsiusScale implements Scale {
    private final String scaleName = "Цельсия";

    @Override
    public String getScaleName() {
        return scaleName;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return scaleName;
    }
}
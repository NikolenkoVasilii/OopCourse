package ru.academits.nikolenko.temperature.model.scale;

public class FahrenheitScale implements Scale {
    private final String scaleName = "Фаренгейта";

    @Override
    public String getScaleName() {
        return scaleName;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature * 1.8) + 32;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public String toString() {
        return scaleName;
    }
}
package ru.academits.nikolenko.temperature.model.scale;

public class CelsiusScale implements Scale {
    @Override
    public String getScaleName() {
        return "Celsius";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return inputTemperature;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}
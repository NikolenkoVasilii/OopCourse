package ru.academits.nikolenko.temperature.model.scale;

public class KelvinScale implements Scale {
    @Override
    public String getScaleName() {
        return "Kelvin";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature + 273.15;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return inputTemperature - 273.15;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}
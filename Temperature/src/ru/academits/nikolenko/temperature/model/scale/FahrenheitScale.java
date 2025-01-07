package ru.academits.nikolenko.temperature.model.scale;

public class FahrenheitScale implements Scale {
    @Override
    public String getScaleName() {
        return "Fahrenheit";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature * 9 / 5 + 32;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return (inputTemperature - 32) * 5 / 9;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
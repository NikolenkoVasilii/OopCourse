package ru.academits.nikolenko.temperature.model.scale;

public class KelvinScale implements Scale {
    private final String scaleName = "Кельвина";

    @Override
    public String getScaleName() {
        return scaleName;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public String toString() {
        return scaleName;
    }
}
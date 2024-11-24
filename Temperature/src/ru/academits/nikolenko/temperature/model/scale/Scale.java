package ru.academits.nikolenko.temperature.model.scale;

public interface Scale {
    String getScaleName();

    double convertToCelsius(double inputTemperature);
    double convertFromCelsius(double inputTemperature);

    boolean isInputIncorrect(double inputTemperature);
}

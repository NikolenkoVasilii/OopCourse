package ru.academits.nikolenko.temperature.model;

import ru.academits.nikolenko.temperature.model.scale.Scale;

public record TemperatureConverter(Scale[] scales) implements Converter {
    @Override
    public double convert(Scale convertFromScale, Scale convertToScale, double temperature) {
        double celsiusTemperature = convertFromScale.convertFromCelsius(temperature);
        return convertToScale.convertToCelsius(celsiusTemperature);
    }
}
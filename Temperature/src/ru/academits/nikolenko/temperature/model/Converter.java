package ru.academits.nikolenko.temperature.model;

import ru.academits.nikolenko.temperature.model.scale.Scale;

public interface Converter {
    Scale[] scales();

    double convert(Scale convertFromScale, Scale convertToScale, double inputTemperature);
}
package ru.academits.nikolenko.temperature.controller;

import ru.academits.nikolenko.temperature.model.scale.Scale;

public interface Controller {
    Scale[] getScales();
    void convert(Scale convertFromScale, Scale convertToScale, double temperature);
}


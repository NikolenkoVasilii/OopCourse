package ru.academits.nikolenko.temperature.controller;

import ru.academits.nikolenko.temperature.model.Converter;
import ru.academits.nikolenko.temperature.model.scale.Scale;
import ru.academits.nikolenko.temperature.view.View;

public class TemperatureController implements Controller {
    private final Converter converter;
    private final View view;

    public TemperatureController(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    @Override
    public Scale[] getScales() {
        return converter.scales();
    }

    @Override
    public void convert(Scale convertFromScale, Scale convertToScale, double temperature) {
        double convertedTemperature = converter.convert(convertFromScale, convertToScale, temperature);
        view.showTemperature(convertToScale.getScaleName(), convertedTemperature);
    }
}
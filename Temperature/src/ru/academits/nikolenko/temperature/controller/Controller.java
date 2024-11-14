package ru.academits.nikolenko.temperature.controller;

import ru.academits.nikolenko.temperature.model.Converter;
import ru.academits.nikolenko.temperature.model.scale.Scale;
import ru.academits.nikolenko.temperature.view.View;

public  interface Controller {
   Scale[] getScales();

   void convert(Scale convertFromScale, Scale convertToScale, double temperature);
}
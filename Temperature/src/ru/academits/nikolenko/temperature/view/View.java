package ru.academits.nikolenko.temperature.view;

import ru.academits.nikolenko.temperature.controller.Controller;
import ru.academits.nikolenko.temperature.controller.TemperatureController;

public interface View {
    void start();

    void setController(Controller controller);

    void showTemperature(String scaleName, double temperature);
}
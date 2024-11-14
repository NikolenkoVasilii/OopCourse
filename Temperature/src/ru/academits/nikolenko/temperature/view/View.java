package ru.academits.nikolenko.temperature.view;

import ru.academits.nikolenko.temperature.controller.Controller;

import javax.swing.*;

public interface View {
    void setController(Controller controller);

    void start();

    void showTemperature(String scaleNme, double temperature);
}

package ru.academits.nikolenko.temperature.model;

import ru.academits.nikolenko.temperature.model.scale.Scale;

public class Model {
    private double outputTemperature;
    private final Scale[] scales;

    public Model(Scale[] scales) {
        this.scales = scales;
    }


    public double getOutputTemperature() {
        return outputTemperature;
    }

    private void setOutputTemperature(double outputTemperature) {
        this.outputTemperature = outputTemperature;
    }

    public int getScaleIndex(String scaleName) {
        for (int i = 0; i < scales.length; i++) {
            if (scaleName.equals(scales[i].getScaleName())) {
                return i;
            }
        }

        return -1;
    }

    public boolean wasConvertTemperature(double inputTemperature, int scaleInitial, int scaleToConvert) {
        if (scales[scaleInitial].isInputIncorrect(inputTemperature)) {
            return false;
        }

        double valueInCelsius = scales[scaleInitial].convertToCelsius(inputTemperature);
        setOutputTemperature(scales[scaleToConvert].convertFromCelsius(valueInCelsius));
        return true;
    }
}
package ru.academits.nikolenko.temperature.view;

import ru.academits.nikolenko.temperature.controller.Controller;
import ru.academits.nikolenko.temperature.model.TemperatureConverter;
import ru.academits.nikolenko.temperature.model.scale.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DesktopView implements View {

    private Controller controller;
    private final JTextField fieldToInputTemperature = new JTextField(10);
    private final JTextField fieldToOutputTemperature = new JTextField(10);
    private final JButton convertButton = new JButton("Конвертировать");

    private final JPanel panel = new JPanel();
    private double inputTemperature;
    private ButtonGroup buttonGroupInputScales;
    private ButtonGroup buttonGroupScalesToConvert;
    private TemperatureConverter model;
    private boolean isStarted;
    private Scale convertFromScale;
    private Scale convertToScale;

    public void start() {
        if (isStarted) {
            throw new IllegalStateException("Метод start уже был вызван.");
        }

        isStarted = true;

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }


            JFrame frame = new JFrame("Конвертор температур");


            frame.setSize(900, 350);
            frame.setLocation(350, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);

            Scale[] scales = controller.getScales();


            frame.add(panel);
            GridBagLayout gbl = new GridBagLayout();
            panel.setLayout(gbl);
            GridBagConstraints c = new GridBagConstraints();


            c.insets = new Insets(20, 20, 20, 20);
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0;
            c.gridwidth = 2;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.PAGE_END;


            JLabel label1 = new JLabel("Введите начальную температуру");
            gbl.setConstraints(label1, c);
            panel.add(label1);

            c.gridx = 2;
            c.gridy = 0;
            c.weightx = 0;
            c.gridwidth = 3;
            gbl.setConstraints(fieldToInputTemperature, c);
            fieldToInputTemperature.setEditable(true);
            panel.add(fieldToInputTemperature);

            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0;
            c.gridwidth = 2;
            JLabel label2 = new JLabel("Выберите шкалу из которой хотите конвертировать");
            gbl.setConstraints(label2, c);
            panel.add(label2);

            buttonGroupInputScales = new ButtonGroup();
            JRadioButton[] ButtonsFromConvert = new JRadioButton[scales.length];

            c.weightx = 1;
            c.gridwidth = 1;


            for (int i = 0; i < scales.length; i++) {
                ButtonsFromConvert[i] = new JRadioButton((scales[i].getScaleName()));
                c.gridx = i + 2;


                buttonGroupInputScales.add(ButtonsFromConvert[i]);
                gbl.setConstraints(ButtonsFromConvert[i], c);
                panel.add(ButtonsFromConvert[i]);
            }
            if (ButtonsFromConvert[1].isSelected()) {
                convertFromScale = scales[1];
            }
            if (ButtonsFromConvert[0].isSelected()) {
                convertFromScale = scales[0];
            }

            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 0;
            c.gridwidth = 2;

            JLabel labelScalesToConvert = new JLabel("Выберите шкалу в которую хотите конвертировать");
            gbl.setConstraints(labelScalesToConvert, c);
            panel.add(labelScalesToConvert);

            buttonGroupScalesToConvert = new ButtonGroup();
            JRadioButton[] buttonsToConvert = new JRadioButton[scales.length];
            c.weightx = 1;
            c.gridwidth = 1;


            for (int i = 0; i < scales.length; i++) {
                buttonsToConvert[i] = new JRadioButton(String.valueOf((scales[i])));
                c.gridx = i + 2;


                buttonGroupScalesToConvert.add(buttonsToConvert[i]);
                gbl.setConstraints(buttonsToConvert[i], c);
                panel.add(buttonsToConvert[i]);


            }
            if (buttonsToConvert[0].isSelected()) {
                convertToScale = scales[0];

            }
            if (buttonsToConvert[1].isSelected()) {
                convertToScale = scales[1];

            }
            if (buttonsToConvert[2].isSelected()) {
                convertToScale = scales[2];

            }


            c.gridx = 0;
            c.gridy = 3;
            c.weightx = 1;
            c.gridwidth = 6;
            c.fill = GridBagConstraints.BOTH;

            gbl.setConstraints(convertButton, c);

            for (int i = 0; i < scales.length; i++) {
                convertToScale = ButtonsFromConvert[i].setActionCommand(scales[i].getScaleName());
                buttonsToConvert[i].setActionCommand(scalesSet[i]);
            }

            convertButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(fieldToInputTemperature.getText());
                    controller.convert(convertFromScale, convertToScale, temperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(convertButton);

            c.gridx = 0;
            c.gridy = 4;
            c.gridwidth = 2;
            c.weightx = 1;
            c.fill = GridBagConstraints.BOTH;

            JLabel label5 = new JLabel("Результат конвертации: ");
            gbl.setConstraints(label5, c);
            panel.add(label5);

            c.gridx = 2;
            c.gridy = 4;
            c.gridwidth = 3;
            gbl.setConstraints(fieldToOutputTemperature, c);
            fieldToOutputTemperature.setEditable(false);

            panel.add(fieldToOutputTemperature);

            frame.setVisible(true);

        });
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showTemperature(String scaleName, double temperature) {
        fieldToOutputTemperature.setText("Температура в градусах " + scaleName + " = " + temperature);
    }


}
package ru.academits.nikolenko.temperature.view;
import ru.academits.nikolenko.temperature.model.Model;
import ru.academits.nikolenko.temperature.model.scale.Scale;

import javax.swing.*;
import java.awt.*;


public class DesktopView {
    private final JTextField fieldToInputTemperature = new JTextField(10);
    private final JTextField fieldToOutputTemperature = new JTextField(10);
    private final JButton buttonConvert = new JButton("Конвертировать");
    private final JFrame frame = new JFrame("Конвертор температур");
    private final JPanel panel = new JPanel();
    private double inputTemperature;
    private final ButtonGroup buttonGroupInputScales;
    private final ButtonGroup buttonGroupScalesToConvert;
    private final Model model;

    public DesktopView(Scale[] scales) {
        model = new Model(scales);
        int scalesCount = scales.length;
        String[] scalesSet = new String[scalesCount];

        for (int i = 0; i < scalesCount; i++) {
            scalesSet[i] = scales[i].getScaleName();
        }

        frame.setSize(900, 350);
        frame.setLocation(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

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
        JRadioButton[] radioButtons = new JRadioButton[scalesCount];

        c.weightx = 1;
        c.gridwidth = 1;

        for (int i = 0; i < scalesCount; i++) {
            radioButtons[i] = new JRadioButton(scalesSet[i]);
            c.gridx = i + 2;

            buttonGroupInputScales.add(radioButtons[i]);
            gbl.setConstraints(radioButtons[i], c);
            panel.add(radioButtons[i]);
        }

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.gridwidth = 2;
        JLabel labelScalesToConvert = new JLabel("Выберите шкалу в которую хотите конвертировать");
        gbl.setConstraints(labelScalesToConvert, c);
        panel.add(labelScalesToConvert);

        buttonGroupScalesToConvert = new ButtonGroup();
        JRadioButton[] buttonsToConvert = new JRadioButton[scalesSet.length];
        c.weightx = 1;
        c.gridwidth = 1;

        for (int i = 0; i < scalesCount; i++) {
            buttonsToConvert[i] = new JRadioButton(scalesSet[i]);
            c.gridx = i + 2;
            buttonGroupScalesToConvert.add(buttonsToConvert[i]);
            gbl.setConstraints(buttonsToConvert[i], c);
            panel.add(buttonsToConvert[i]);
        }

        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.gridwidth = 6;
        c.fill = GridBagConstraints.BOTH;

        gbl.setConstraints(buttonConvert, c);
        panel.add(buttonConvert);


        for (int i = 0; i < scalesSet.length; i++) {
            radioButtons[i].setActionCommand(scalesSet[i]);
            buttonsToConvert[i].setActionCommand(scalesSet[i]);
        }

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
    }

    public void setVisible(boolean bool) {
        frame.setVisible(bool);
    }

    private void displayError(String messageError) {
        JOptionPane.showMessageDialog(panel, messageError);
    }

    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            buttonConvert.addActionListener(e -> {
                String text = fieldToInputTemperature.getText();

                if (!isNumeric(text)) {

                    displayError("Введите число!");
                } else {
                    inputTemperature = Double.parseDouble(text);

                    String stringInitialScale = buttonGroupInputScales.getSelection().getActionCommand();
                    String stringScaleToConvert = buttonGroupScalesToConvert.getSelection().getActionCommand();

                    int scaleInitial = model.getScaleIndex(stringInitialScale);
                    int scaleToConvert = model.getScaleIndex(stringScaleToConvert);

                    if (model.wasConvertTemperature(inputTemperature, scaleInitial, scaleToConvert)) {
                        double outputTemperature = model.getOutputTemperature();
                        fieldToOutputTemperature.setText(String.format("%.2f", outputTemperature));
                    } else {
                        fieldToInputTemperature.setText("");
                        fieldToOutputTemperature.setText("");
                        displayError("Значение температуры некорректно для данной шкалы");
                    }
                }
            });
        });
    }

    private static boolean isNumeric(String stringNumber) {
        try {
            Double.parseDouble(stringNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
package ru.academits.nikolenko.temperature.view;

import ru.academits.nikolenko.temperature.controller.Controller;
import ru.academits.nikolenko.temperature.model.scale.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DesktopView implements View, ItemListener {
    private Controller controller;
    private JLabel resultLabel;
    private JPanel startPanel;
    private boolean isStarted;
    private Scale convertFromScale;
    private Scale convertToScale;

    @Override
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

            JFrame frame = new JFrame("Конвертер температур");
            frame.setLocationRelativeTo(null);
            frame.setMinimumSize(new Dimension(450, 120));
            frame.setResizable(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            startPanel = new JPanel();
            startPanel.add(new JLabel("Конвертировать температуру из градусов"));

            Scale[] scales = controller.getScales();

            JComboBox<Scale> convertFromScalesComboBox = new JComboBox<>(scales);
            convertFromScalesComboBox.setEditable(false);
            convertFromScale = scales[0];
            convertFromScalesComboBox.addActionListener(e -> convertFromScale = (Scale) convertFromScalesComboBox.getSelectedItem());
            startPanel.add(convertFromScalesComboBox);

            JTextField temperatureField = new JTextField(10);
            startPanel.add(temperatureField);

            JPanel centerPanel = new JPanel();
            centerPanel.add(new JLabel("в градусы"));

            Scale temp = scales[0];
            scales[0] = scales[1];
            scales[1] = temp;

            JComboBox<Scale> convertToScalesComboBox = new JComboBox<>(scales);
            convertToScalesComboBox.setEditable(false);
            convertToScale = scales[0];
            convertToScalesComboBox.addActionListener(e -> convertToScale = (Scale) convertToScalesComboBox.getSelectedItem());

            centerPanel.add(convertToScalesComboBox);

            JButton converterButton = new JButton("Конвертировать");
            converterButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(temperatureField.getText());
                    controller.convert(convertFromScale, convertToScale, temperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            centerPanel.add(converterButton);

            resultLabel = new JLabel();
            JPanel resultPanel = new JPanel();
            resultPanel.add(resultLabel);

            Container container = frame.getContentPane();
            container.add(startPanel, BorderLayout.PAGE_START);
            container.add(centerPanel, BorderLayout.CENTER);
            container.add(resultPanel, BorderLayout.PAGE_END);

            frame.setVisible(true);
        });
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showTemperature(String scaleName, double temperature) {
        resultLabel.setText("Температура в градусах " + scaleName + " = " + temperature);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        CardLayout layout = (CardLayout) startPanel.getLayout();
        layout.show(startPanel, (String) event.getItem());
    }
}
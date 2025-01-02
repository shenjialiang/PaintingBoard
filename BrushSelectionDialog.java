package com.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrushSelectionDialog extends JDialog {

    public BrushSelectionDialog(JFrame parent, PaintCanvas canvas) {
        super(parent, "Brush Settings", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);
        setLayout(new FlowLayout());

        JComboBox<String> shapeComboBox = new JComboBox<>(new String[]{"Pencil", "Circle", "Rectangle", "Curve"});
        shapeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String shape = (String) shapeComboBox.getSelectedItem();
                Brush brush = new Brush();
                brush.setShape(shape);
                canvas.setBrush(brush);
            }
        });

        JSpinner thicknessSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));
        thicknessSpinner.addChangeListener(e -> {
            Brush brush = canvas.getCurrentBrush();
            brush.setThickness((int) thicknessSpinner.getValue());
        });

        JButton colorButton = new JButton("Select Color");
        colorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Brush Color", Color.BLACK);
            if (color != null) {
                Brush brush = canvas.getCurrentBrush();
                brush.setColor(color);
            }
        });

        JCheckBox randomWritingCheckBox = new JCheckBox("Enable Random Writing");
        randomWritingCheckBox.addActionListener(e -> {
            boolean enabled = randomWritingCheckBox.isSelected();
            Brush brush = canvas.getCurrentBrush();
            brush.setRandomWritingEnabled(enabled);  // 调用setRandomWritingEnabled
        });

        JComboBox<String> transparencyComboBox = new JComboBox<>(new String[]{"No Transparency", "Simple Transparency"});
        transparencyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTransparency = (String) transparencyComboBox.getSelectedItem();
                Brush brush = canvas.getCurrentBrush();
                if ("No Transparency".equals(selectedTransparency)) {
                    brush.setTransparencyStrategy(new NoTransparencyStrategy());
                } else if ("Simple Transparency".equals(selectedTransparency)) {
                    brush.setTransparencyStrategy(new SimpleTransparencyStrategy());
                }
            }
        });

        add(shapeComboBox);
        add(thicknessSpinner);
        add(colorButton);
        add(randomWritingCheckBox);
        add(transparencyComboBox);
        setVisible(true);
    }
}

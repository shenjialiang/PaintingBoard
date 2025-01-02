package com.paint;

import java.awt.Color;
import java.awt.Point;  // 添加导入语句

public class Brush {

    private Color color;
    private int thickness;
    private String shape;
    private boolean randomWritingEnabled;
    private TransparencyStrategy transparencyStrategy;

    public Brush() {
        this.color = Color.BLACK;
        this.thickness = 5;
        this.shape = "Pencil";
        this.randomWritingEnabled = false;
        this.transparencyStrategy = new NoTransparencyStrategy();
    }

    public Shape createShape(Point start, Point end) {
        Color adjustedColor = transparencyStrategy.adjustTransparency(color, 0.5f);
        if (randomWritingEnabled) {
            return new RandomWriting(start, end, adjustedColor, thickness);
        }

        switch (shape) {
            case "Circle":
                return new Ellipse(start, end, adjustedColor, thickness);
            case "Rectangle":
                return new Rectangle(start, end, adjustedColor, thickness);
            case "Line":
                return new Line(start, end, adjustedColor, thickness);
            case "Curve":
                return new Curve(start, end, adjustedColor, thickness);
            default:
                return new Line(start, end, adjustedColor, thickness);
        }
    }

    // 添加方法：启用或禁用随机书写
    public void setRandomWritingEnabled(boolean enabled) {
        this.randomWritingEnabled = enabled;
    }

    public void setTransparencyStrategy(TransparencyStrategy transparencyStrategy) {
        this.transparencyStrategy = transparencyStrategy;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }

    public String getShape() {
        return shape;
    }
}
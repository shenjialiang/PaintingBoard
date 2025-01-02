package com.paint;

import java.awt.*;

public class Line implements Shape {
    private Point start, end;
    private Color color;
    private int thickness;

    public Line(Point start, Point end, Color color, int thickness) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.thickness = thickness;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawLine(start.x, start.y, end.x, end.y);
    }
}
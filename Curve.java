package com.paint;

import java.awt.*;
import java.util.ArrayList;

public class Curve implements Shape {
    private ArrayList<Point> points;
    private Color color;
    private int thickness;

    public Curve(Point start, Point end, Color color, int thickness) {
        points = new ArrayList<>();
        points.add(start);
        points.add(end);
        this.color = color;
        this.thickness = thickness;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}

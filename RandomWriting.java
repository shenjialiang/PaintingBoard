package com.paint;

import java.awt.*;
import java.util.Random;

public class RandomWriting implements Shape {
    private Point start, end;
    private Color color;
    private int thickness;
    private Random random;

    public RandomWriting(Point start, Point end, Color color, int thickness) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.thickness = thickness;
        this.random = new Random();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));

        // 随机偏移量控制
        int offsetX = random.nextInt(5) - 2; // 随机偏移在 -2 到 2 之间
        int offsetY = random.nextInt(5) - 2;

        // 动态生成随机手写效果
        int currentX = end.x + offsetX;
        int currentY = end.y + offsetY;

        g2d.drawLine(start.x, start.y, currentX, currentY); // 绘制从起点到当前点的线段
    }

    // 提供一个方法更新位置，使得手写效果随着鼠标动态移动
    public void updateEnd(Point newEnd) {
        this.end = newEnd;
    }
}

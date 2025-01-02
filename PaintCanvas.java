package com.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PaintCanvas extends JPanel {

    private Brush currentBrush;
    private HistoryManager historyManager;
    private BackgroundManager backgroundManager;
    private Shape currentShape;
    private Point startPoint;

    public PaintCanvas() {
        currentBrush = new Brush();
        historyManager = new HistoryManager();
        backgroundManager = new BackgroundManager();
        setBackground(Color.WHITE);
        initListeners();
    }

    private void initListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Shape shape = currentShape;
                if (shape != null) {
                    historyManager.addHistory(shape);
                    repaint();
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentShape = currentBrush.createShape(startPoint, e.getPoint());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundManager.paintBackground(g);  // 绘制背景
        if (currentShape != null) {
            // 确保当前绘制的形状使用正确的颜色
            currentShape.draw(g);
        }
        historyManager.paintHistory(g);  // 绘制历史记录中的形状
    }

    public void undo() {
        historyManager.undo();
        repaint();
    }

    public void redo() {
        historyManager.redo();
        repaint();
    }

    public void setBrush(Brush brush) {
        this.currentBrush = brush;
    }

    public Brush getCurrentBrush() {
        return currentBrush;
    }

    public void setBackgroundImage(Image image) {
        backgroundManager.setBackgroundImage(image);
        repaint();
    }

    public void saveAsJPG() {
        try {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
            paint(g2d); // 将当前的绘图内容绘制到BufferedImage
            g2d.dispose();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Image");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPG Image", "jpg"));
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                ImageIO.write(image, "jpg", fileToSave);
                JOptionPane.showMessageDialog(this, "Image saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

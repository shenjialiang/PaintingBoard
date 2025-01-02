package com.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.*;

public class PaintApp {

    // 创建 Logger 对象
    private static final Logger logger = Logger.getLogger(PaintApp.class.getName());

    public static void main(String[] args) {
        // 配置日志
        setupLogger();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ShenJialiang 2024124587");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            PaintCanvas canvas = new PaintCanvas();
            frame.add(canvas, BorderLayout.CENTER);

            // 设置工具栏
            JPanel toolBar = new JPanel();

            JButton brushButton = new JButton("Brush");
            brushButton.addActionListener(e -> {
                logger.info("Brush button clicked");
                new BrushSelectionDialog(frame, canvas);
                logger.info("Brush dialog opened successfully.");
            });

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> {
                logger.info("Save button clicked");
                // 保存画布为 JPG
                canvas.saveAsJPG(); // 不再尝试获取返回值，直接执行方法
                logger.info("Canvas save operation initiated.");
            });

            JButton loadBackgroundButton = new JButton("Load Background");
            loadBackgroundButton.addActionListener(e -> {
                logger.info("Load Background button clicked");
                // 加载背景图
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPG Images", "jpg", "jpeg"));
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        Image backgroundImage = ImageIO.read(file);
                        canvas.setBackgroundImage(backgroundImage);
                        logger.info("Background image loaded successfully from: " + file.getAbsolutePath());
                    } catch (IOException ex) {
                        logger.severe("Error loading image from file " + file.getAbsolutePath() + ": " + ex.getMessage());
                        JOptionPane.showMessageDialog(frame, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    logger.warning("User canceled background image load operation.");
                }
            });

            JButton undoButton = new JButton("Undo");
            undoButton.addActionListener(e -> {
                logger.info("Undo button clicked");
                canvas.undo(); // 不再尝试获取返回值，直接执行方法
                logger.info("Undo operation executed.");
            });

            JButton redoButton = new JButton("Redo");
            redoButton.addActionListener(e -> {
                logger.info("Redo button clicked");
                canvas.redo(); // 不再尝试获取返回值，直接执行方法
                logger.info("Redo operation executed.");
            });

            toolBar.add(brushButton);
            toolBar.add(saveButton);
            toolBar.add(loadBackgroundButton);
            toolBar.add(undoButton);
            toolBar.add(redoButton);

            frame.add(toolBar, BorderLayout.NORTH);
            frame.setVisible(true);

            logger.info("Paint application started successfully");
        });
    }

    // 设置 Logger 的配置
    private static void setupLogger() {
        try {
            // 获取 Logger 的全局日志管理器
            LogManager.getLogManager().reset();

            // 配置日志格式
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);

            // 自定义日志格式
            String format = "%1$tF %1$tT [%4$s] %2$s - %5$s%6$s%n";
            Formatter formatter = new java.util.logging.Formatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format(format,
                            record.getMillis(),
                            record.getLoggerName(),
                            record.getSourceClassName(),
                            record.getLevel(),
                            record.getMessage(),
                            record.getThrown() == null ? "" : "\n" + record.getThrown());
                }
            };
            consoleHandler.setFormatter(formatter);

            // 配置 Logger
            Logger logger = Logger.getLogger(PaintApp.class.getName());
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}

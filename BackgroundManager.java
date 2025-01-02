package com.paint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundManager {

    private BufferedImage backgroundImage;

    public void setBackgroundImage(Image image) {
        if (image instanceof BufferedImage) {
            this.backgroundImage = (BufferedImage) image;
        }
    }

    public void paintBackground(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }
    }
}
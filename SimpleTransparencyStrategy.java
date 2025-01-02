package com.paint;

import java.awt.Color;

public class SimpleTransparencyStrategy implements TransparencyStrategy {
    @Override
    public Color adjustTransparency(Color color, float transparency) {
        int alpha = (int) (transparency * 255);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
}
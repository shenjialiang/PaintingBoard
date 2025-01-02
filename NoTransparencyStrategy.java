package com.paint;

import java.awt.Color;

public class NoTransparencyStrategy implements TransparencyStrategy {
    @Override
    public Color adjustTransparency(Color color, float transparency) {
        return color;
    }
}
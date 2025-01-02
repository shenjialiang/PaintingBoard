package com.paint;

import java.awt.Color;

public interface TransparencyStrategy {
    Color adjustTransparency(Color color, float transparency);
}
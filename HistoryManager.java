package com.paint;

import java.awt.*;
import java.util.Stack;

public class HistoryManager {

    private Stack<Shape> history;
    private Stack<Shape> redoStack;

    public HistoryManager() {
        history = new Stack<>();
        redoStack = new Stack<>();
    }

    public void addHistory(Shape shape) {
        history.push(shape);
        redoStack.clear();  // 新操作后，清空重做栈
    }

    public void undo() {
        if (!history.isEmpty()) {
            redoStack.push(history.pop());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            history.push(redoStack.pop());
        }
    }

    public void paintHistory(Graphics g) {
        for (Shape shape : history) {
            shape.draw(g);
        }
    }
}
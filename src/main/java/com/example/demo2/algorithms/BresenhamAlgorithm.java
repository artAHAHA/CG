package com.example.demo2.algorithms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BresenhamAlgorithm {

    public static void drawLine(GraphicsContext gc, int x1, int y1, int x2, int y2, Color startColor, Color endColor) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;

        int steps = Math.max(dx, dy);  // Количество шагов для изменения цвета

        // Получение RGB компонентов начального и конечного цветов
        double r1 = startColor.getRed();
        double g1 = startColor.getGreen();
        double b1 = startColor.getBlue();

        double r2 = endColor.getRed();
        double g2 = endColor.getGreen();
        double b2 = endColor.getBlue();

        // Разница цветов для каждого шага
        double rStep = (r2 - r1) / steps;
        double gStep = (g2 - g1) / steps;
        double bStep = (b2 - b1) / steps;

        int step = 0;

        while (true) {
            // Интерполируем текущий цвет
            double currentR = r1 + rStep * step;
            double currentG = g1 + gStep * step;
            double currentB = b1 + bStep * step;

            // Устанавливаем текущий цвет для рисования
            gc.setStroke(new Color(currentR, currentG, currentB, 1.0));
            gc.strokeRect(x1, y1, 1, 1);

            if (x1 == x2 && y1 == y2) break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }

            step++;
        }
    }
}

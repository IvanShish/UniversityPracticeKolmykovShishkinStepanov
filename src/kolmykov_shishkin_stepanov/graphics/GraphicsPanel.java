package kolmykov_shishkin_stepanov.graphics;

import kolmykov_shishkin_stepanov.Node;
import kolmykov_shishkin_stepanov.algorithm.Edge;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GraphicsPanel extends JPanel {
    private final double RADIUS = 300; // было 450, на 13-дюймовый экран не все вершины влезают
    private final double H = 50;
    private final double W = 50;
    private final double X0 = 800;
    private final double Y0 = 450;
    private final int RECT_H = 15;
    private final int RECT_W = 15;

    public void drawGraph(Node[] nodes) { //Для каждой вершины отрисовывается вершина и ребра к ее соседям
        clear();
        for (Node node : nodes) {
            drawNode(node.getNumber(), nodes.length);
            Map<Integer, Edge> edgesMap = node.getEdgesMap();
            for (Integer neighbourNumber : edgesMap.keySet()) {
                drawEdge(node.getNumber(), neighbourNumber, edgesMap.get(neighbourNumber).getCapacity(), nodes.length, edgesMap.get(neighbourNumber).getColor());
            }
        }
    }

    public void drawNode(int number, int quantity) {
        double angle = Math.PI* 2/quantity * number;
        double x = X0 + RADIUS * Math.cos(angle);
        double y = Y0 + RADIUS * Math.sin(angle);
        drawCircle(x, y, W, H);
        drawNumber(number, x, y, Color.WHITE);
    }

    public void drawEdge(int number1, int number2, int value, int quantity, Color color) {
        double angle1 = Math.PI* 2/quantity * number1;
        double x1 = X0 + RADIUS * Math.cos(angle1) + 20;
        double y1 = Y0 + RADIUS * Math.sin(angle1) + 30;

        double angle2 = Math.PI* 2/quantity * number2;
        double x2 = X0 + RADIUS * Math.cos(angle2) + 20;
        double y2 = Y0 + RADIUS * Math.sin(angle2) + 30;

        drawLine(x1, y1, x2, y2, color);
        drawSquare((int)((x1 + x2)/2), (int)((y1 + y2)/2 - 10), RECT_W, RECT_H);
        drawNumber(value, (x1 + x2 - 40)/2, (y1 + y2 - 60)/2, Color.BLACK);
        drawNode(number1, quantity);
        drawNode(number2, quantity);
    }

    private void drawLine(double x1, double y1, double x2, double y2, Color color) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setColor(color);
        graphics2D.setBackground(color);
        graphics2D.setStroke(new BasicStroke(4));
        graphics2D.draw(new Line(x1, y1, x2, y2));
        graphics2D.dispose();
    }

    private void drawCircle(double x, double y, double w, double h) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setColor(new Color(158, 8, 8));
        graphics2D.fill(new Circle(x, y, w, h));
        graphics2D.dispose();
    }

    private void drawNumber(int number, double x, double y, Color color) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setColor(color);
        graphics2D.drawString(String.valueOf(number), (int)x + 20, (int)y + 30);
    }

    private void drawSquare(int x, int y, int w, int h) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(x, y, w, h);
    }

    private void clear() {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(300, 0, 1000, 1000);
    }
}

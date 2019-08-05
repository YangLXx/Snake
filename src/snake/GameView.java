package snake;

import java.awt.*;
import javax.swing.*;

import static snake.Settings.*;

public class GameView {
    private final Grid grid;
    private JPanel canvas;


    public GameView(Grid grid) {
        this.grid = grid;
    }

    public void init() {
        canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics graphics) {
                drawGridBackground(graphics);
                drawSnake(graphics, grid.getSnake());
                drawSnakeHead(graphics, grid.getSnake());
                drawFood(graphics, grid.getFood());
            }
        };
    }

    public void draw() {
        canvas.repaint();
    }

    public void drawSnake(Graphics graphics, Snake snake) {
        for (Node i : snake.getBody()) {
            drawSquare(graphics, i, SNAKE_COLOR);
        }
    }

    public void drawSnakeHead(Graphics graphics, Snake snake) {
        drawSquare(graphics, snake.getHead(), SNAKE_HEAD_COLOR);
    }

    public void drawFood(Graphics graphics, Node food) {
        drawCircle(graphics, food, FOOD_COLOR);
    }

    public void drawGridBackground(Graphics graphics) {
        graphics.setColor(GRID_BACKGROUND_COLOR);
        int size = DEFAULT_NODE_SIZE;
        graphics.fillRect(0,0, grid.getWidth() * size , grid.getHeight() * size);
    }

    private void drawSquare(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = DEFAULT_NODE_SIZE;
        graphics.fillRect(squareArea.X() * size, squareArea.Y() * size, size - 1, size - 1);
    }

    private void drawCircle(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = DEFAULT_NODE_SIZE;
        graphics.fillOval(squareArea.X() * size, squareArea.Y() * size, size, size);
    }

    public JPanel getCanvas() { return canvas; }
}

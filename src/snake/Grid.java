package snake;

import java.util.LinkedList;
import java.util.Random;

public class Grid {
    private final int height;
    private final int width;
    public boolean[][] isOccupied;
    private static int score = 0;
    private static boolean changedDirectionInThisRound = false;

    Snake snake;
    private snake.Node food;
    private Direction snakeDirection = Direction.LEFT;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        isOccupied = new boolean[width][height];

        initSnake();
        createFood();
    }

    private Snake initSnake() {
        int length = width / 4;
        LinkedList<Node> body = new LinkedList<>();
        for (int i = 0; i <= length; i++) {
            body.addLast(new Node((width / 2) + i, height / 2));
            isOccupied[body.getLast().X()][body.getLast().Y()] = true;
        }

        snake = new Snake(body);
        return snake;
    }

    public Node createFood() {
        boolean validPosition = false;
        int x, y;

        while (!validPosition) {
            Random ran = new Random();
            x = ran.nextInt(width);
            y = ran.nextInt(height);

            if (!isOccupied[x][y]) {
                food = new Node(x, y);
                validPosition = true;
            }
        }
        return food;
    }

    public boolean nextRound() {
        Node removedTail = snake.move(snakeDirection);
        int headX = snake.getHead().X();
        int headY = snake.getHead().Y();

        changedDirectionInThisRound = false;

        if (headX < 0 || headY < 0 || headX >= width || headY >= height) {
            return false;
        } else if (isOccupied[headX][headY]) {
            return false;
        } else if (headX == food.X() && headY == food.Y()) {
            snake.eat(removedTail);
            score += 1;
            createFood();
            isOccupied[headX][headY] = true;
            return true;
        } else {
            isOccupied[removedTail.X()][removedTail.Y()] = false;
            isOccupied[headX][headY] = true;
            return true;
        }
    }

    public void changeDirection(Direction newDirection) {
        if (snakeDirection.compatibleWith(newDirection) && !changedDirectionInThisRound) {
            snakeDirection = newDirection;
            changedDirectionInThisRound = true;
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Node getFood() {
        return food;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getScore() {
        return score;
    }
}

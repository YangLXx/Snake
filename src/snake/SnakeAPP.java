package snake;

import javax.swing.*;
import java.awt.*;

import static snake.Settings.*;

public class SnakeAPP implements Runnable{
    public void run() {
        Grid grid = new Grid(GRID_HEIGHT, GRID_WIDTH);

        JFrame window = new JFrame("Snake");

        Container contentPane = window.getContentPane();

        GameView gameView = new GameView(grid);
        gameView.init();

        gameView.getCanvas().setPreferredSize(new Dimension(GRID_WIDTH * DEFAULT_NODE_SIZE, GRID_HEIGHT * DEFAULT_NODE_SIZE));

        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);

        window.pack();
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        GameController gameController = new GameController(grid, gameView);
        window.addKeyListener(gameController);

        new Thread(gameController).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SnakeAPP());
    }
}

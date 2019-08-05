package snake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static snake.Settings.DEFAULT_MOVE_INTERVAL;

public class GameController implements Runnable, KeyListener {
    private final Grid grid;
    private final GameView gameView;

    private boolean running;

    public GameController (Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep((DEFAULT_MOVE_INTERVAL));
            } catch (InterruptedException e) {
                break;
            }

            if (grid.nextRound()) {
                gameView.draw();
            } else {
                gameView.draw();
                showGameOverMessage();
                break;
            }
        }
        running = false;
    }

    public void showGameOverMessage() {
        JOptionPane.showMessageDialog(null, "GAME OVER \n Score:" + grid.getScore(), "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_I) {
            grid.changeDirection(Direction.UP);
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_J) {
            grid.changeDirection(Direction.LEFT);
        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_K) {
            grid.changeDirection(Direction.DOWN);
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_L) {
            grid.changeDirection(Direction.RIGHT);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

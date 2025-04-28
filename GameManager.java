import javax.swing.*;
import java.awt.*;

class GameManager {
    private final JFrame frame;
    private final CardLayout layout = new CardLayout();
    private final JPanel container = new JPanel(layout);

    private GamePanel gamePanel;
    private GameMenu menuPanel;
    private GameOverScreen gameOverPanel;

    public GameManager(JFrame frame) {
        this.frame = frame;
        frame.setContentPane(container);
        menuPanel = new GameMenu(this);
        container.add(menuPanel, "menu");
    }

    public void showMenu() {
        layout.show(container, "menu");
    }

    public void startGame(GameConfig config) {
        if (gamePanel != null) container.remove(gamePanel);
        gamePanel = new GamePanel(this, config);
        container.add(gamePanel, "game");
        layout.show(container, "game");
        gamePanel.requestFocusInWindow();
    }

    public void showGameOver(int score) {
        if (gameOverPanel != null) container.remove(gameOverPanel);
        gameOverPanel = new GameOverScreen(this, score);
        container.add(gameOverPanel, "gameover");
        layout.show(container, "gameover");
    }
}

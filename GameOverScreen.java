import javax.swing.*;
import java.awt.*;


class GameOverScreen extends JPanel {
    private final Image background;

    public GameOverScreen(GameManager manager, int score) {
        setLayout(null);
        setPreferredSize(new Dimension(360, 640));

        background = new ImageIcon(getClass().getResource("/assets/flappybird-1.png")).getImage();

        JLabel label = new JLabel("Game Over", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setBounds(80, 100, 200, 50);

        JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreLabel.setBounds(80, 150, 200, 40);

        JButton retry = new JButton("Try Again");
        retry.setBounds(130, 210, 100, 30);
        retry.addActionListener(e -> manager.showMenu());

        JButton exit = new JButton("Exit");
        exit.setBounds(130, 250, 100, 30);
        exit.addActionListener(e -> System.exit(0));

        add(label);
        add(scoreLabel);
        add(retry);
        add(exit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}

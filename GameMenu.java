import javax.swing.*;
import java.awt.*;

class GameMenu extends JPanel {
    private final Image background;

    public GameMenu(GameManager manager) {
        setLayout(null);
        setPreferredSize(new Dimension(360, 640));

        background = new ImageIcon(getClass().getResource("/assets/flappybird-1.png")).getImage();

        JLabel title = new JLabel(" Flappy Bird", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setBounds(80, 50, 200, 50);

        String[] levels = {"Easy", "Medium", "Hard"};
        JComboBox<String> levelSelector = new JComboBox<>(levels);
        levelSelector.setBounds(110, 150, 140, 30);

        JButton start = new JButton("Start");
        start.setBounds(130, 200, 100, 30);
        start.addActionListener(e -> {
            String selected = (String) levelSelector.getSelectedItem();
            if (selected.equals("Easy")) manager.startGame(GameConfig.easy());
            else if (selected.equals("Medium")) manager.startGame(GameConfig.medium());
            else manager.startGame(GameConfig.hard());
        });

        JButton exit = new JButton("Exit");
        exit.setBounds(130, 240, 100, 30);
        exit.addActionListener(e -> System.exit(0));

        add(title);
        add(levelSelector);
        add(start);
        add(exit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}

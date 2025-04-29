import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static java.awt.event.KeyEvent.VK_SPACE;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private final int WIDTH = 360, HEIGHT = 640;
    private final GameManager gameManager;
    private final GameConfig config;

    private Bird bird;
    private Image backgroundImg, birdImg, topPipeImg, bottomPipeImg;
    private ArrayList<Pipe> pipes = new ArrayList<>();

    private int velocityY = 0;
    private double score = 0;
    private boolean gameOver = false;

    private Timer gameTimer;
    private Timer pipeTimer;

    public GamePanel(GameManager gameManager, GameConfig config) {
        this.gameManager = gameManager;
        this.config = config;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        backgroundImg = new ImageIcon(getClass().getResource("/assets/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/assets/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/assets/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/assets/bottompipe.png")).getImage();

        bird = new Bird(WIDTH / 8, HEIGHT / 2, birdImg);

        pipeTimer = new Timer(1500, e -> placePipes());
        pipeTimer.start();

        gameTimer = new Timer(1000 / 60, this);
        gameTimer.start();
    }

    private void placePipes() {
        int gap = HEIGHT / 4;
        int randomY = -100 - (int)(Math.random() * 200);

        Pipe topPipe = new Pipe(WIDTH, randomY, topPipeImg);
        Pipe bottomPipe = new Pipe(WIDTH, randomY + 512 + gap, bottomPipeImg);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes)
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Score: " + (int) score, 10, 35);
    }

    private void updateGame() {
        velocityY += config.gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for (Pipe pipe : pipes) {
            pipe.x += config.velocityX;
            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }
            if (collision(bird, pipe)) gameOver = true;
        }

        if (bird.y > HEIGHT) gameOver = true;
    }

    private boolean collision(Bird b, Pipe p) {
        return b.x < p.x + p.width && b.x + b.width > p.x && b.y < p.y + p.height && b.y + b.height > p.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();

        if (gameOver) {



            gameTimer.stop();
            pipeTimer.stop();
            gameManager.showGameOver((int) score);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;



        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
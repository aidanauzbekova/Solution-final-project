//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.Random;
//
//
//class FlappyBird extends JPanel implements ActionListener, KeyListener {
//    private final GameManager gameManager;
//    private final String difficulty;
//    private final int boardWidth = 360;
//    private final int boardHeight = 640;
//
//    private Image backgroundImg, birdImg, topPipeImg, bottomPipeImg;
//    private int birdX = boardWidth / 8;
//    private int birdY = boardHeight / 2;
//    private int birdWidth = 34, birdHeight = 24;
//    private int pipeWidth = 64, pipeHeight = 512, pipeX = boardWidth;
//
//    private int velocityX;
//    private int velocityY = 0;
//    private int gravity;
//    private boolean gameOver = false;
//    private double score = 0;
//
//    private Bird bird;
//    private final ArrayList<Pipe> pipes = new ArrayList<>();
//    private final Timer gameLoop;
//    private final Timer placePipeTimer;
//
//    class Bird {
//        int x = birdX, y = birdY, width = birdWidth, height = birdHeight;
//        Image img;
//        Bird(Image img) { this.img = img; }
//    }
//
//    class Pipe {
//        int x = pipeX, y = 0, width = pipeWidth, height = pipeHeight;
//        Image img;
//        boolean passed = false;
//        Pipe(Image img) { this.img = img; }
//    }
//
//    public FlappyBird(GameManager gameManager, String difficulty) {
//        this.gameManager = gameManager;
//        this.difficulty = difficulty;
//        setPreferredSize(new Dimension(boardWidth, boardHeight));
//        setFocusable(true);
//        addKeyListener(this);
//
//        // Updated: load images from file system (resources folder)
//        new ImageIcon(getClass().getResource("/assets/flappybird.png")).getImage();
//        new ImageIcon(getClass().getResource("/assets/flappybirdbg.png")).getImage();
//        new ImageIcon(getClass().getResource("/assets/toppipe.png")).getImage();
//        new ImageIcon(getClass().getResource("/assets/bottompipe.png")).getImage();
//        new ImageIcon(getClass().getResource("/assets/flappybird-1.png")).getImage();
//
//        bird = new Bird(birdImg);
//
//        switch (difficulty) {
//            case "Easy": velocityX = -3; gravity = 1; break;
//            case "Medium": velocityX = -4; gravity = 2; break;
//            case "Hard": velocityX = -5; gravity = 3; break;
//        }
//
//        placePipeTimer = new Timer(1500, e -> placePipes());
//        placePipeTimer.start();
//
//        gameLoop = new Timer(1000 / 60, this);
//        gameLoop.start();
//    }
//
//    void placePipes() {
//        int randomY = (int) (-pipeHeight / 4 - Math.random() * (pipeHeight / 2));
//        int gap = boardHeight / 4;
//        Pipe topPipe = new Pipe(topPipeImg);
//        topPipe.y = randomY;
//        Pipe bottomPipe = new Pipe(bottomPipeImg);
//        bottomPipe.y = topPipe.y + pipeHeight + gap;
//        pipes.add(topPipe);
//        pipes.add(bottomPipe);
//    }
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }
//
//    public void draw(Graphics g) {
//        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
//        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
//        for (Pipe pipe : pipes)
//            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
//        g.setColor(Color.white);
//        g.setFont(new Font("Arial", Font.PLAIN, 32));
//        g.drawString(gameOver ? "Game Over: " + (int) score : String.valueOf((int) score), 10, 35);
//    }
//
//    public void move() {
//        velocityY += gravity;
//        bird.y += velocityY;
//        bird.y = Math.max(bird.y, 0);
//
//        for (Pipe pipe : pipes) {
//            pipe.x += velocityX;
//            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
//                score += 0.5;
//                pipe.passed = true;
//            }
//            if (collision(bird, pipe)) gameOver = true;
//        }
//
//        if (bird.y > boardHeight) gameOver = true;
//    }
//
//    boolean collision(Bird a, Pipe b) {
//        return a.x < b.x + b.width && a.x + a.width > b.x && a.y < b.y + b.height && a.y + a.height > b.y;
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        move();
//        repaint();
//        if (gameOver) {
//            gameLoop.stop();
//            placePipeTimer.stop();
//            gameManager.showGameOver((int) score);
//        }
//    }
//
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            velocityY = -9;
//        }
//    }
//
//    public void keyTyped(KeyEvent e) {}
//    public void keyReleased(KeyEvent e) {}
//}

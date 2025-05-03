import java.awt.*;

public class ScoreManager {
    private int score = 0;

    public void increment() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 30);
    }
}
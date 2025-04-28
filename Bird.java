import java.awt.*;

class Bird {
    public int x, y, width = 34, height = 24;
    public Image img;

    public Bird(int startX, int startY, Image img) {
        this.x = startX;
        this.y = startY;
        this.img = img;
    }
} 
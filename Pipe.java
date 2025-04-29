import java.awt.*;

class Pipe {
    public int x, y, width = 64, height = 512;
    public boolean passed = false;
    public Image img;

    public Pipe(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
}
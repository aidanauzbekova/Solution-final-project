class GameConfig {
    public final int gravity;
    public final int velocityX;

    public GameConfig(int gravity, int velocityX) {
        this.gravity = gravity;
        this.velocityX = velocityX;
    }

    public static GameConfig easy() { return new GameConfig(1, -3); }
    public static GameConfig medium() { return new GameConfig(2, -4); }
    public static GameConfig hard() { return new GameConfig(3, -5); }
}
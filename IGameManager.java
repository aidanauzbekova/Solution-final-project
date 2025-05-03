public interface IGameManager {
    void showMenu();
    void startGame();
    void exitGame();
    void showGameOver(int score); // новый метод
}
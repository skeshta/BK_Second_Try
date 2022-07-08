public class BowlingGame {
    private int gameScore = 0;

    //Get score
    public int getScore() {
        return gameScore;
    }

    public int nextFrame(int rollOne, int rollTwo) {
        gameScore += rollOne + rollTwo;
        return gameScore;
    }

}

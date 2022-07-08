public class BowlingGame {
    private int gameScore = 0;

    //Get score
    public int getScore() {
        return gameScore;
    }

    public int nextFrame(int[] frameScore) {
        gameScore += frameScore[0] + frameScore[1];
        return gameScore;
    }

}

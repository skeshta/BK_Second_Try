public class BowlingGame {
    private int gameScore = 0;

    //Get score
    public int getScore() {
        return gameScore;
    }

    public int nextFrame(int rollOne, int rollTwo) {
        int error_value = -2147483648;
        if (rollOne + rollTwo > 10 || Math.min(rollOne, rollTwo) < 0) {
            return error_value;
        }
        else {
            gameScore += rollOne + rollTwo;
            return gameScore;
        }
    }

}

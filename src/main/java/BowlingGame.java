public class BowlingGame {

    public boolean frameValid(int rollOne, int rollTwo) {
        boolean bothRollsOK;
        bothRollsOK = rollOne + rollTwo <= 10 && Math.min(rollOne, rollTwo) >= 0;
        return bothRollsOK;
    }

    public int scoreGameTotal(int[] frames) {
        int gameScore = 0;
        return gameScore;
    }

    public int scoreSingleFrame(int rollOne, int rollTwo) {
        int error_value = -2147483648;
        int frameScore = 0;
        if (!frameValid(rollOne, rollTwo)) {
            return error_value;
        }
        else {
            frameScore += rollOne + rollTwo;
            return frameScore;
        }
    }

}

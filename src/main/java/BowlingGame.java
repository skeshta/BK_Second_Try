public class BowlingGame {
    private final int error_value = -2147483648;

    private boolean frameValid(int rollOne, int rollTwo) {
        boolean bothRollsOK;
        bothRollsOK = rollOne + rollTwo <= 10 && Math.min(rollOne, rollTwo) >= 0;
        return bothRollsOK;
    }

    private boolean gameValid(int[] frames) {
        boolean allFramesOK;
        allFramesOK = (frames.length % 2 == 0);
        return allFramesOK;
    }

    public int scoreGameTotal(int[] frames) {
        int gameScore = 0;

        if (!gameValid(frames)) {
            return error_value;
        }

        int totalRolls = frames.length;
        int scoreCurrentFrame = 0;
        for (int i=0; i<totalRolls; i+=2) {
            scoreCurrentFrame = scoreSingleFrame(frames[i], frames[i+1]);
            if (scoreCurrentFrame == 10) {
                scoreCurrentFrame += frames[i+2];
            }
            gameScore += scoreCurrentFrame;
        }
        return gameScore;
    }

    public int scoreSingleFrame(int rollOne, int rollTwo) {
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

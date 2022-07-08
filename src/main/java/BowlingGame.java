public class BowlingGame {
    private final int error_value = -2147483648;

    private boolean frameValid(int rollOne, int rollTwo) {
        boolean bothRollsOK;
        bothRollsOK = rollOne + rollTwo <= 10 && Math.min(rollOne, rollTwo) >= 0;
        return bothRollsOK;
    }

    private boolean gameValid(int[] frames) {
        boolean allFramesOK;
        int totalRolls = frames.length;
        allFramesOK = totalRolls % 2 == 0;
        if (totalRolls > 22) {
            allFramesOK = false;
        } else if (totalRolls == 22) {
            int lastFrameRollOne = frames[18];
            int lastFrameRollTwo = frames[19];
            if (frameIsASpare(lastFrameRollOne, lastFrameRollTwo)) {
                int bonusFrameRollTwo = frames[21];
                allFramesOK = (bonusFrameRollTwo == 0);
            }
        }
        return allFramesOK;
    }

    private boolean frameIsAStrike(int rollOne, int rollTwo) {
        boolean isStrike;
        isStrike = (rollOne == 10 && rollTwo == 0);
        return isStrike;
    }

    private boolean frameIsASpare(int rollOne, int rollTwo) {
        boolean isSpare;
        isSpare = scoreSingleFrame(rollOne, rollTwo) == 10 && rollOne != 10;
        return isSpare;
    }

    public int scoreGameTotal(int[] frames) {
        int gameScore = 0;

        if (!gameValid(frames)) {
            return error_value;
        } else {
            int totalRolls = Math.min(frames.length, 20);
            int scoreCurrentFrame;
            int scoreNextFrame;
            int rollOne;
            int rollTwo;
            for (int currentRoll = 0; currentRoll < totalRolls; currentRoll += 2) {
                rollOne = frames[currentRoll];
                rollTwo = frames[currentRoll + 1];
                scoreCurrentFrame = scoreSingleFrame(rollOne, rollTwo);

                if (frameIsAStrike(rollOne, rollTwo)) {
                    scoreNextFrame = scoreSingleFrame(frames[currentRoll + 2], frames[currentRoll + 3]);
                    scoreCurrentFrame += scoreNextFrame;
                } else if (frameIsASpare(rollOne, rollTwo)) {
                    scoreCurrentFrame += frames[currentRoll + 2];
                }
                gameScore += scoreCurrentFrame;
            }
            return gameScore;
        }
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

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
            int currentRollOne;
            int currentRollTwo;
            int nextRollOne;
            int nextRollTwo;
            for (int currentRoll = 0; currentRoll < totalRolls; currentRoll += 2) {
                currentRollOne = frames[currentRoll];
                currentRollTwo = frames[currentRoll + 1];
                scoreCurrentFrame = scoreSingleFrame(currentRollOne, currentRollTwo);

                if (frameIsAStrike(currentRollOne, currentRollTwo)) {
                    nextRollOne = frames[currentRoll + 2];
                    nextRollTwo = frames[currentRoll + 3];
                    scoreNextFrame = scoreSingleFrame(nextRollOne, nextRollTwo);
                    if (currentRoll != 18) {
                        scoreCurrentFrame += scoreNextFrame;
                        if (nextRollOne == 10) {
                            scoreCurrentFrame += frames[currentRoll + 4];
                        }
                    } else {
                        scoreCurrentFrame += (frames[currentRoll + 2] + frames[currentRoll + 3]);
                    }
                } else if (frameIsASpare(currentRollOne, currentRollTwo)) {
                    scoreCurrentFrame += frames[currentRoll + 2];
                }
                gameScore += scoreCurrentFrame;
            }
            return gameScore;
        }
    }

    public int scoreSingleFrame(int rollOne, int rollTwo) {
        int frameScore;
        if (!frameValid(rollOne, rollTwo)) {
            return error_value;
        }
        else {
            frameScore = rollOne + rollTwo;
            return frameScore;
        }
    }

}

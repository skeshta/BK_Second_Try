public class BowlingGame {
    private BowlingFrame[] frames;
    private int numberOfFrames;
    private final int error_value = -2147483648;

    public int getScore(int[] rolls) {
        setGame(rolls);
        return score();
    }

    public void setGame(int[] rolls) {
        int numberOfRolls = rolls.length;
        if (numberOfRolls % 2 != 0) {
            // If the last genuine frame is a spare, there may be one extra roll.
            // In that case, just add a 0 to that "frame."
            int[] cleanFrameRolls = new int[numberOfRolls + 1];
            System.arraycopy(rolls, 0, cleanFrameRolls, 0, numberOfRolls);
            cleanFrameRolls[numberOfRolls + 1] = 0;
            numberOfRolls++;
            rolls = cleanFrameRolls;
        }

        // Convert the scores into proper frames.
        numberOfFrames = numberOfRolls / 2;
        frames = new BowlingFrame[numberOfFrames + 1];
        BowlingFrame currentFrame;
        int rollIndex;
        for (int frameIndex = 0; frameIndex < numberOfFrames; frameIndex++) {
            rollIndex = frameIndex * 2;
            currentFrame = new BowlingFrame();
            currentFrame.setFrame(rolls[rollIndex], rolls[rollIndex + 1]);
            frames[frameIndex] = currentFrame;
        }
    }

    private boolean gameValid() {
        boolean allFramesOK;
        int numberOfFrames = frames.length;
        allFramesOK = numberOfFrames <= 10;
        if (numberOfFrames == 11) {
            BowlingFrame lastGenuineFrame = frames[9];
            if (lastGenuineFrame.isASpare()) {
                int bonusFrameRollTwo = frames[10].getRollTwo();
                allFramesOK = (bonusFrameRollTwo == 0);
            }
        }
        return allFramesOK;
    }

    public int score() {
        int gameScore = 0;

        if (!gameValid()) {
            return error_value;
        } else {
            int numberOfGenuineFrames = Math.min(numberOfFrames, 10);
            int scoreCurrentFrame;
            int scoreNextFrame;
            BowlingFrame currentFrame;
            BowlingFrame nextFrame;
            nextFrame = new BowlingFrame();
            for (int frameIndex = 0; frameIndex < numberOfGenuineFrames; frameIndex ++) {
                currentFrame = frames[frameIndex];
                scoreCurrentFrame = currentFrame.frameSum();
                if (currentFrame.isAStrike()) {
                    nextFrame = frames[frameIndex +1];
                    scoreNextFrame = nextFrame.frameSum();
                    if (frameIndex != 9) {
                        scoreCurrentFrame += scoreNextFrame;
                        if (nextFrame.isAStrike()) {
                            scoreCurrentFrame += frames[frameIndex + 2].getRollOne();
                        }
                    } else {
                        scoreCurrentFrame += nextFrame.frameSum();
                    }
                } else if (currentFrame.isASpare()) {
                    scoreCurrentFrame += nextFrame.getRollTwo();
                }
                gameScore += scoreCurrentFrame;
            }
            return gameScore;
        }
    }

}

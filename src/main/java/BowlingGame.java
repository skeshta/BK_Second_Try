public class BowlingGame {
    private BowlingFrame[] frames;
    private int numberOfFrames;
    private final int error_value = -2147483648;

    public int getScore(int[] rolls) {
        setGame(rolls);
        return score();
    }

    public void setGame(int[] rolls) {
        BowlingFrame[] cleanFrames;
        cleanFrames = cleanInput(rolls);
        frames = cleanFrames;
    }

    private int[] oddRollsToEven(int[] rolls) {
        int numberOfRolls = rolls.length;
        int[] evenRolls;
        if (numberOfRolls % 2 != 0) {
            // There is a half frame. Fill its second half with a 0.
            evenRolls = new int[numberOfRolls + 1];
            System.arraycopy(rolls, 0, evenRolls, 0, numberOfRolls);
            evenRolls[numberOfRolls + 1] = 0;
        } else {
            evenRolls = new int[numberOfRolls];
        }
        return evenRolls;
    }

    private int[] cutOffExtraRolls(int[] rolls) {
        int[] shortRolls;
        int numberOfRolls = rolls.length;
        if (numberOfRolls > 22) {
            shortRolls = new int[22];
            System.arraycopy(rolls, 0, shortRolls, 0, 22);
        } else {
            shortRolls = rolls;
        }
        return shortRolls;
    }

    private BowlingFrame[] rollsToFrames(int[] rolls) {
        BowlingFrame[] frames;
        numberOfFrames = rolls.length / 2;
        frames = new BowlingFrame[numberOfFrames + 1];
        BowlingFrame currentFrame;
        int rollIndex;
        for (int frameIndex = 0; frameIndex < numberOfFrames; frameIndex++) {
            rollIndex = frameIndex * 2;
            currentFrame = new BowlingFrame();
            currentFrame.setFrame(rolls[rollIndex], rolls[rollIndex + 1]);
            frames[frameIndex] = currentFrame;
        }
        return frames;
    }

    private BowlingFrame[] cleanFinalFrame(BowlingFrame[] frames) {
        BowlingFrame[] cleanFrames;
        int numberOfFrames = frames.length;
        if (numberOfFrames < 10) {
            // The game is not finished. There is no final frame, as it were.
            cleanFrames = frames;
        } else {
            BowlingFrame finalFrame = frames[9];
            if (numberOfFrames == 10) {
                if (finalFrame.isRegular()) {
                    cleanFrames = frames;
                } else {
                    // The final frame is a spare or strike, but there are no bonus rolls.
                    // Fill the bonus frame with (0, 0).
                    cleanFrames = new BowlingFrame[11];
                    BowlingFrame bonusFrame = new BowlingFrame();
                    bonusFrame.setFrame(0, 0);
                    cleanFrames[10] = bonusFrame;
                }
            } else {
                BowlingFrame bonusFrame = frames[10];
                if (finalFrame.isASpare()) {
                    if (bonusFrame.getRollTwo() == 0) {
                        cleanFrames = frames;
                    } else {
                        //There is a 22nd roll that should not exist. Remove it.
                        cleanFrames = new BowlingFrame[11];
                        System.arraycopy(frames, 0, cleanFrames, 0, 10);
                        int bonusRoll = bonusFrame.getRollOne();
                        BowlingFrame cleanBonusFrame = new BowlingFrame();
                        cleanBonusFrame.setFrame(bonusRoll, 0);
                        cleanFrames[10] = cleanBonusFrame;
                    }
                } else if (finalFrame.isAStrike()) {
                    cleanFrames = frames;
                } else {
                    // The final frame is regular, but there are extra rolls. Remove them.
                    cleanFrames = new BowlingFrame[10];
                    System.arraycopy(frames, 0, cleanFrames, 0, 10);
                }
            }
        }
        return cleanFrames;
    }

    private BowlingFrame[] cleanInput(int[] rolls) {
        int numberOfRolls = rolls.length;
        int[] cleanRolls;
        BowlingFrame[] cleanFrames;
        cleanRolls = oddRollsToEven(rolls);
        cleanRolls = cutOffExtraRolls(cleanRolls);
        cleanFrames = rollsToFrames(cleanRolls);
        cleanFrames = cleanFinalFrame(cleanFrames);
        return cleanFrames;
    }

    private boolean gameValid() {
        boolean allFramesOK;
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

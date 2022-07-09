import java.util.Arrays;

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
            evenRolls[numberOfRolls] = 0;
        } else {
            evenRolls = rolls;
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
        int[] cleanRolls;
        BowlingFrame[] cleanFrames;
        cleanRolls = oddRollsToEven(rolls);
        cleanRolls = cutOffExtraRolls(cleanRolls);
        cleanFrames = rollsToFrames(cleanRolls);
        cleanFrames = cleanFinalFrame(cleanFrames);
        return cleanFrames;
    }

    public int score() {
        int gameScore = 0;
        int numberOfFrames = Math.min(frames.length, 10) - 1;
        int numberOfGenuineFrames = numberOfFrames - 1;
        int[] frameScores = new int[numberOfFrames];
        int scoreCurrentFrame;

        BowlingFrame currentFrame = new BowlingFrame();
        int currentFrameScore;
        for (int frameIndex = 0; frameIndex < numberOfFrames; frameIndex++) {
            currentFrame = frames[frameIndex];
            currentFrameScore = currentFrame.frameSum();
            frameScores[frameIndex] = currentFrameScore;
        }

        BowlingFrame nextFrame;
        int bonusScore;
        for (int frameIndex = 0; frameIndex < numberOfGenuineFrames; frameIndex++) {
            currentFrame = frames[frameIndex];
            currentFrameScore = frameScores[frameIndex];
            if (currentFrame.isASpare()) {
                nextFrame = frames[frameIndex + 1];
                bonusScore = nextFrame.getRollOne();
                currentFrameScore += bonusScore;
                frameScores[frameIndex] = currentFrameScore;
            } else if (currentFrame.isAStrike()) {
                nextFrame = frames[frameIndex + 1];
                bonusScore = nextFrame.frameSum();
                currentFrameScore += bonusScore;
                frameScores[frameIndex] = currentFrameScore;
            }
        }

        for (int frameIndex = 0; frameIndex < numberOfFrames; frameIndex++) {
            currentFrameScore = frameScores[frameIndex];
            gameScore += currentFrameScore;
        }
        return gameScore;
    }

}

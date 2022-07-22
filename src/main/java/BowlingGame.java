public class BowlingGame {
    private BowlingFrame[] frames;
    private int finalFrameIndex;

    public int getScore(BowlingFrame[] framesIn) {
        int totalScore = 0;
        int currentScore;
        frames = framesIn;
        finalFrameIndex = frames.length - 1;
        BowlingFrame currentFrame;
        for (int index = 0; index <= finalFrameIndex; index++) {
            currentFrame = frames[index];
            currentScore = currentFrame.sumRolls() + bonusScore(index);
            totalScore += currentScore;
        }
        return totalScore;
    }

    public BowlingFrame[] stringToFrames(String rolls) {
        BowlingFrame[] framesOut = new BowlingFrame[10];
        for (int index = 0; index < 10; index++) {
            framesOut[index] = new BowlingFrame();
        }
        BowlingFrame currentFrame;
        for (int index = 0; index < 6; index++) {
            currentFrame = framesOut[index];
            currentFrame.set(index, index);
        }
        for (int index = 6; index < 9; index++) {
            currentFrame = framesOut[index];
            currentFrame.set(index, 10 - index);
        }
        currentFrame = framesOut[9];
        currentFrame.set(10, 0, 4, 2);
        return framesOut;
    }

    private int bonusScore(int index) {
        int bonus;
        if (index < finalFrameIndex) {
            bonus = bonusScoreOngoing(index);
        } else {
            bonus = bonusScoreFinal();
        }
        return bonus;
    }

    private int bonusScoreOngoing(int index) {
        int bonus = 0;
        BowlingFrame currentFrame = frames[index];
        BowlingFrame nextFrame = frames[index + 1];
        if (currentFrame.isSpare()) {
            bonus = nextFrame.getRollOne();
        } else if (currentFrame.isStrike()) {
            bonus = nextFrame.sumRolls();
            if (nextFrame.isStrike()) {
                if (index + 2 < finalFrameIndex) {
                    BowlingFrame frameAfterNext = frames[index + 2];
                    bonus += frameAfterNext.getRollOne();
                } else {
                    bonus += nextFrame.getExtraRollOne();
                }
            }
        }
        return bonus;
    }

    private int bonusScoreFinal() {
        int bonus = 0;
        BowlingFrame currentFrame = frames[finalFrameIndex];
        if (currentFrame.isSpare() || currentFrame.isStrike()) {
            // This works because we're assuming good inputs.
            bonus = currentFrame.getBonusRoll();
        }
        return bonus;
    }

}

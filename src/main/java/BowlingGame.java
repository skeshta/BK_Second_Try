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

    public BowlingFrame[] stringToFrames(String rolls, int numberOfFrames) {
        /*
        For simplicity, let's assume good inputs.
        */
        BowlingFrame[] framesOut = new BowlingFrame[numberOfFrames];
        BowlingFrame currentFrame = new BowlingFrame();
        char currentChar = rolls.charAt(0);
        char nextChar = rolls.charAt(1);
        int stringIndexNextFrame = 3;
        int rollOne = 0;
        int rollTwo = 0;
        int extraRollOne = 0;
        int extraRollTwo = 0;
        if (numberOfFrames > 1) {
            if (currentChar == 'X') {
                rollOne = 10;
                stringIndexNextFrame--;
            } else if (nextChar == '/') {
                rollOne = Character.getNumericValue(currentChar);
                rollTwo = 10 - rollOne;
            } else {
                if (currentChar != '-') {
                    rollOne = Character.getNumericValue(currentChar);
                }
                if (nextChar != '-') {
                    rollTwo = Character.getNumericValue(nextChar);
                }
            }
            rolls = rolls.substring(stringIndexNextFrame);
            BowlingFrame[] nextRolls = stringToFrames(rolls, numberOfFrames - 1);
            // Yes, copying the arrays into each other is inefficient.
            System.arraycopy(nextRolls, 0, framesOut, 1, numberOfFrames - 1);
        } else {
            currentFrame = new BowlingFrame();
            if (currentChar == 'X') {
                rollOne = 10;
                char finalChar;
                finalChar = rolls.charAt(2);
                extraRollOne = Character.getNumericValue(nextChar);
                extraRollTwo = Character.getNumericValue(finalChar);
            } else if (nextChar == '/') {
                rollOne = currentChar;
                rollTwo = 10 - rollOne;
                extraRollOne = Character.getNumericValue(nextChar);
            } else {
                if (currentChar != '-') {
                    rollOne = Character.getNumericValue(currentChar);
                }
                if (nextChar != '-') {
                    rollTwo = Character.getNumericValue(nextChar);
                }
            }
        }
        currentFrame.set(rollOne, rollTwo, extraRollOne, extraRollTwo);
        framesOut[0] = currentFrame;
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

public class BowlingGame {
    private BowlingFrame[] frames;
    private int finalFrameIndex = 9;
    private int totalScore = 0;

    public int getScore(String rolls) {
        //int totalScore = 0;
        int currentScore;
        frames = stringToFrames(rolls, 10);
        //finalFrameIndex = frames.length - 1;
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
        char currentRoll = rolls.charAt(0);
        char nextRoll = rolls.charAt(1);
        int stringIndexNextFrame = 3;
        int rollOne = 0;
        int rollTwo = 0;
        int extraRollOne = 0;
        int extraRollTwo = 0;
        if (currentRoll == 'X') {
            rollOne = 10;
            stringIndexNextFrame--;
            if (numberOfFrames == 1) {
                if (nextRoll == 'X') {
                    extraRollOne = 10;
                } else if (nextRoll != '-') {
                    extraRollOne = Character.getNumericValue(nextRoll);
                }
                char rollAfterNext;
                rollAfterNext = rolls.charAt(2);
                if (rollAfterNext == 'X') {
                    extraRollTwo = 10;
                } else if (rollAfterNext != '-') {
                    extraRollTwo = Character.getNumericValue(rollAfterNext);
                }
                //extraRollOne = Character.getNumericValue(nextChar);
                //extraRollTwo = Character.getNumericValue(charAfterNext);
            }
        } else if (nextRoll == '/') {
            rollOne = Character.getNumericValue(currentRoll);
            rollTwo = 10 - rollOne;
            if (numberOfFrames == 1) {
                char rollAfterNext;
                rollAfterNext = rolls.charAt(2);
                if (rollAfterNext == 'X') {
                    extraRollOne = 10;
                } else if (rollAfterNext != '-') {
                    extraRollOne = Character.getNumericValue(rollAfterNext);
                }
            }
        } else {
            if (currentRoll != '-') {
                rollOne = Character.getNumericValue(currentRoll);
            }
            if (nextRoll != '-') {
                rollTwo = Character.getNumericValue(nextRoll);
            }
        }
        currentFrame.set(rollOne, rollTwo, extraRollOne, extraRollTwo);
        framesOut[0] = currentFrame;
        if (numberOfFrames > 1) {
            rolls = rolls.substring(stringIndexNextFrame);
            BowlingFrame[] nextRolls = stringToFrames(rolls, numberOfFrames - 1);
            // Yes, copying the arrays into each other is inefficient.
            System.arraycopy(nextRolls, 0, framesOut, 1, numberOfFrames - 1);
        }
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
                if (index + 1 < finalFrameIndex) {
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
            bonus = currentFrame.getBonusRoll();
        }
        return bonus;
    }

}

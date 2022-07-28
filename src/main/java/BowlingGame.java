public class BowlingGame {
    private final int numberOfFrames = 10;
    private final int finalFrameIndex = numberOfFrames - 1;
    private final char strikeChar = 'X';
    private final char spareChar = '/';
    private final char missChar = '-';
    private final int maxPins = 10;
    private int totalScore = 0;
    private BowlingFrame[] frames = new BowlingFrame[numberOfFrames];

    public int getGameScore(String rolls) {
        int currentScore;
        frames = stringToFrames(rolls);
        BowlingFrame currentFrame;
        for (int index = 0; index < numberOfFrames; index++) {
            currentFrame = frames[index];
            if (currentFrame.isStrike()) {
                currentScore = currentFrame.getRollOne() + bonusScore(index);
            } else {
                currentScore = currentFrame.sumRollsOneAndTwo() + bonusScore(index);
            }
            totalScore += currentScore;
        }
        return totalScore;
    }

    public BowlingFrame[] stringToFrames(String rolls) {
        BowlingFrame[] framesOut = new BowlingFrame[numberOfFrames];
        BowlingFrame currentFrame;
        int NextFrameStringIndex;
        for (int index = 0; index < finalFrameIndex; index++) {
            currentFrame = stringToFrameOngoing(rolls);
            currentFrame.setMaxPins(maxPins);
            framesOut[index] = currentFrame;
            if (currentFrame.isStrike()) {
                NextFrameStringIndex = 2;
            } else {
                NextFrameStringIndex = 3;
            }
            rolls = rolls.substring(NextFrameStringIndex);
        }
        framesOut[finalFrameIndex] = stringToFrameFinal(rolls);
        framesOut[finalFrameIndex].setMaxPins(maxPins);
        return framesOut;
    }

    private BowlingFrame stringToFrameOngoing(String rolls) {
        int rollOne = 0;
        int rollTwo = 0;
        BowlingFrame currentFrame = new BowlingFrame();
        char currentRoll = rolls.charAt(0);
        char nextRoll = rolls.charAt(1);
        if (currentRoll == strikeChar) {
            rollOne = maxPins;
        } else if (nextRoll == spareChar) {
            if (currentRoll != missChar) {
                rollOne = Character.getNumericValue(currentRoll);
            }
            rollTwo = maxPins - rollOne;
        } else {
            if (currentRoll != missChar) {
                rollOne = Character.getNumericValue(currentRoll);
            }
            if (nextRoll != missChar) {
                rollTwo = Character.getNumericValue(nextRoll);
            }
        }
        currentFrame.setRolls(rollOne, rollTwo);
        return currentFrame;
    }

    private BowlingFrame stringToFrameFinal(String rolls) {
        int rollOne = 0;
        int rollTwo = 0;
        int rollThree = 0;
        BowlingFrame currentFrame = new BowlingFrame();
        char currentRoll = rolls.charAt(0);
        char nextRoll = rolls.charAt(1);
        if (currentRoll == strikeChar) {
            char rollAfterNext = rolls.charAt(2);
            rollOne = maxPins;
            if (nextRoll == strikeChar) {
                rollTwo = maxPins;
            } else if (nextRoll != missChar) {
                rollTwo = Character.getNumericValue(nextRoll);
            }
            if (rollAfterNext == strikeChar) {
                rollThree = maxPins;
            } else if (rollAfterNext != missChar) {
                rollThree = Character.getNumericValue(rollAfterNext);
            }
        } else if (nextRoll == spareChar) {
            char rollAfterNext = rolls.charAt(2);
            if (currentRoll != missChar) {
                rollOne = Character.getNumericValue(currentRoll);
            }
            rollTwo = maxPins - rollOne;
            if (rollAfterNext == strikeChar) {
                rollThree = maxPins;
            } else if (rollAfterNext != missChar) {
                rollThree = Character.getNumericValue(rollAfterNext);
            }
        } else {
            if (currentRoll != missChar) {
                rollOne = Character.getNumericValue(currentRoll);
            }
            if (nextRoll != missChar) {
                rollTwo = Character.getNumericValue(nextRoll);
            }
        }
        currentFrame.setRolls(rollOne, rollTwo, rollThree);
        return currentFrame;
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
            bonus = nextFrame.sumRollsOneAndTwo();
            if (nextFrame.isStrike()) {
                if (index + 1 < finalFrameIndex) {
                    BowlingFrame frameAfterNext = frames[index + 2];
                    bonus += frameAfterNext.getRollOne();
                } /*else {
                    System.out.println("Bonus = " + bonus);
                    bonus += nextFrame.getRollThree();
                    System.out.println("Bonus = " + bonus);
                }*/
            }
        }
        return bonus;
    }

    private int bonusScoreFinal() {
        int bonus = 0;
        BowlingFrame currentFrame = frames[finalFrameIndex];
        if (currentFrame.isSpare()) {
            bonus = currentFrame.getRollThree();
        } else if (currentFrame.isStrike()) {
            bonus = currentFrame.sumRollsTwoAndThree();
        }
        return bonus;
    }

}

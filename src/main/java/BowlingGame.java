public class BowlingGame {
    private int numberOfFrames;
    private int numberOfPins;
    private int finalFrameIndex;
    private char strikeChar;
    private char spareChar;
    private char missChar;
    private int gameScore;
    private BowlingFrame[] frames;

    public void setup(int maxPins, int maxFrames, char strike, char spare, char miss) {
        numberOfFrames = maxFrames;
        numberOfPins = maxPins;
        finalFrameIndex = numberOfFrames - 1;
        strikeChar = strike;
        spareChar = spare;
        missChar = miss;
        gameScore = 0;
    }

    public BowlingFrame[] getFrames() {
        return frames;
    }

    public int getGameScore() {
        for (int index = 0; index < finalFrameIndex; index++) {
            gameScore += scoreOngoingFrame(index);
        }
        gameScore += scoreFinalFrame();
        return gameScore;
    }

    public void setFrames(String rolls) {
        frames = new BowlingFrame[numberOfFrames];
        BowlingFrame currentFrame;
        int startOfNextFrame;
        for (int index = 0; index < finalFrameIndex; index++) {
            currentFrame = stringToFrameOngoing(rolls);
            currentFrame.setMaxPins(numberOfPins);
            frames[index] = currentFrame;
            if (currentFrame.isStrike()) {
                startOfNextFrame = 2;
            } else {
                startOfNextFrame = 3;
            }
            rolls = rolls.substring(startOfNextFrame);
        }
        frames[finalFrameIndex] = stringToFrameFinal(rolls);
        frames[finalFrameIndex].setMaxPins(numberOfPins);
    }

    private BowlingFrame stringToFrameOngoing(String rolls) {
        int rollOne = 0;
        int rollTwo = 0;
        BowlingFrame currentFrame = new BowlingFrame();
        char currentRoll = rolls.charAt(0);
        char nextRoll = rolls.charAt(1);
        if (currentRoll == strikeChar) {
            rollOne = numberOfPins;
        } else if (nextRoll == spareChar) {
            if (currentRoll != missChar) {
                rollOne = Character.getNumericValue(currentRoll);
            }
            rollTwo = numberOfPins - rollOne;
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
            rollOne = numberOfPins;
            if (nextRoll == strikeChar) {
                rollTwo = numberOfPins;
            } else if (nextRoll != missChar) {
                rollTwo = Character.getNumericValue(nextRoll);
            }
            if (rollAfterNext == strikeChar) {
                rollThree = numberOfPins;
            } else if (rollAfterNext != missChar) {
                rollThree = Character.getNumericValue(rollAfterNext);
            }
        } else if (nextRoll == spareChar) {
            char rollAfterNext = rolls.charAt(2);
            if (currentRoll != missChar) {
                rollOne = Character.getNumericValue(currentRoll);
            }
            rollTwo = numberOfPins - rollOne;
            if (rollAfterNext == strikeChar) {
                rollThree = numberOfPins;
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

    private int scoreOngoingFrame(int index) {
        BowlingFrame frame;
        frame = frames[index];
        int regularScore;
        int bonusScore;
        if (frame.isStrike()) {
            regularScore = frame.getRollOne();
        } else {
            regularScore = frame.getRollOne() + frame.getRollTwo();
        }
        bonusScore = bonusScoreOngoing(index);
        return regularScore + bonusScore;
    }

    private int bonusScoreOngoing(int index) {
        int bonusScore = 0;
        BowlingFrame currentFrame = frames[index];
        BowlingFrame nextFrame = frames[index + 1];
        if (currentFrame.isSpare()) {
            bonusScore = nextFrame.getRollOne();
        } else if (currentFrame.isStrike()) {
            bonusScore = nextFrame.getRollOne() + nextFrame.getRollTwo();
            if (nextFrame.isStrike()) {
                if (index + 1 < finalFrameIndex) {
                    BowlingFrame frameAfterNext = frames[index + 2];
                    bonusScore += frameAfterNext.getRollOne();
                }
            }
        }
        return bonusScore;
    }

    private int scoreFinalFrame() {
        BowlingFrame frame = frames[finalFrameIndex];
        return frame.getRollOne() + frame.getRollTwo() + frame.getRollThree();
    }

}

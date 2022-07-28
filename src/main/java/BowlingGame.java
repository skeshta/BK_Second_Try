public class BowlingGame {
    private int numberOfFrames;
    private int numberOfPins;
    private int finalFrameIndex;
    private char strikeChar;
    private char spareChar;
    private char missChar;
    private char separatorChar;
    private int gameScore;
    private BowlingFrame[] frames;

    public void setup(int maxPins, int maxFrames, char strike, char spare, char miss, char separator) {
        numberOfFrames = maxFrames;
        numberOfPins = maxPins;
        finalFrameIndex = numberOfFrames - 1;
        strikeChar = strike;
        spareChar = spare;
        missChar = miss;
        separatorChar = separator;
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
            frames[index] = currentFrame;
            if (currentFrame.isStrike()) {
                startOfNextFrame = 2;
            } else {
                startOfNextFrame = 3;
            }
            rolls = rolls.substring(startOfNextFrame);
        }
        frames[finalFrameIndex] = stringToFrameFinal(rolls);
    }

    private int extractRoll(String rolls, int stringIndex, int previousRoll) {
        int currentRoll = 0;
        char currentChar = rolls.charAt(stringIndex);
        if (currentChar == strikeChar) {
            currentRoll = numberOfPins;
        } else if (currentChar == spareChar) {
            currentRoll = numberOfPins - previousRoll;
        } else if (currentChar != missChar && currentChar != separatorChar) {
        currentRoll = Character.getNumericValue(currentChar);
        }
        return currentRoll;
    }

    private BowlingFrame stringToFrameOngoing(String rolls) {
        int rollOne = extractRoll(rolls, 0, 0);
        int rollTwo = extractRoll(rolls, 1, rollOne);
        BowlingFrame currentFrame = new BowlingFrame();
        currentFrame.setMaxPins(numberOfPins);
        currentFrame.setRolls(rollOne, rollTwo);
        return currentFrame;
    }

    private BowlingFrame stringToFrameFinal(String rolls) {
        int rollOne = extractRoll(rolls, 0, 0);
        int rollTwo = extractRoll(rolls, 1, rollOne);
        int rollThree = 0;
        char secondRoll = rolls.charAt(1);
        if (rollOne == numberOfPins || secondRoll == spareChar) {
            rollThree = extractRoll(rolls, 2, 0);
        }
        BowlingFrame currentFrame = new BowlingFrame();
        currentFrame.setMaxPins(numberOfPins);
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

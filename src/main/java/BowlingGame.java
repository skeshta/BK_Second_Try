public class BowlingGame {
    private BowlingFrame[] frames;
    private final int numberOfFrames = 10;
    private final int finalFrameIndex = numberOfFrames - 1;
    private int totalScore = 0;

    public int getScore(String rolls) {
        int currentScore;
        frames = stringToFrames(rolls);
        BowlingFrame currentFrame;
        for (int index = 0; index <= finalFrameIndex; index++) {
            currentFrame = frames[index];
            currentScore = currentFrame.sumRolls() + bonusScore(index);
            totalScore += currentScore;
        }
        return totalScore;
    }

    public BowlingFrame[] stringToFrames(String rolls) {
        frames = new BowlingFrame[numberOfFrames];
        BowlingFrame currentFrame;
        int stringIndexNextFrame;
        for (int index = 0; index < finalFrameIndex; index++) {
            currentFrame = extractFirstFrame(rolls, index);
            frames[index] = currentFrame;
            if (currentFrame.isStrike()) {
                stringIndexNextFrame = 2;
            } else {
                stringIndexNextFrame = 3;
            }
            rolls = rolls.substring(stringIndexNextFrame);
        }
        frames[finalFrameIndex] = extractFirstFrame(rolls, finalFrameIndex);
        return frames;
    }

    private BowlingFrame extractFirstFrame(String rolls, int index) {
        char strikeChar = 'X';
        char spareChar = '/';
        char missChar = '-';
        int strikeRoll = 10;
        int rollOne = 0;
        int rollTwo = 0;
        int extraRollOne = 0;
        int extraRollTwo = 0;
        BowlingFrame currentFrame = new BowlingFrame();
        char currentRoll = rolls.charAt(0);
        char nextRoll = rolls.charAt(1);
        if (currentRoll == strikeChar) {
            rollOne = strikeRoll;
            if (index == finalFrameIndex) {
                if (nextRoll == strikeChar) {
                    extraRollOne = strikeRoll;
                } else if (nextRoll != missChar) {
                    extraRollOne = Character.getNumericValue(nextRoll);
                }
                char rollAfterNext;
                rollAfterNext = rolls.charAt(2);
                if (rollAfterNext == strikeChar) {
                    extraRollTwo = strikeRoll;
                } else if (rollAfterNext != missChar) {
                    extraRollTwo = Character.getNumericValue(rollAfterNext);
                }
            }
        } else if (nextRoll == spareChar) {
            rollOne = Character.getNumericValue(currentRoll);
            rollTwo = strikeRoll - rollOne;
            if (index == finalFrameIndex) {
                char rollAfterNext;
                rollAfterNext = rolls.charAt(2);
                if (rollAfterNext == strikeChar) {
                    extraRollOne = 10;
                } else if (rollAfterNext != missChar) {
                    extraRollOne = Character.getNumericValue(rollAfterNext);
                }
            }
        } else {
            if (currentRoll != missChar) {
                rollOne = Character.getNumericValue(currentRoll);
            }
            if (nextRoll != missChar) {
                rollTwo = Character.getNumericValue(nextRoll);
            }
        }
        currentFrame.set(rollOne, rollTwo, extraRollOne, extraRollTwo);
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

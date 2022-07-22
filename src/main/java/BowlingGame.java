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
        /*
        For simplicity, let's assume good inputs.
        */
        BowlingFrame[] framesOut = new BowlingFrame[10];
        BowlingFrame currentFrame;
        int stringIndexCurrentFrame = 0;
        int stringIndexNextFrame;
        char currentChar;
        char nextChar;
        int rollOne = 0;
        int rollTwo = 0;
        int extraRollOne;
        int extraRollTwo;
        for (int index = 0; index < 9; index++) {
            currentFrame = new BowlingFrame();
            currentChar = rolls.charAt(stringIndexCurrentFrame);
            nextChar = rolls.charAt(stringIndexCurrentFrame + 1);
            System.out.println("index: " + index);
            System.out.println("currentChar: " + currentChar);
            if (currentChar == 'X') {
                rollOne = 10;
                currentFrame.set(rollOne, rollTwo);
                stringIndexNextFrame = 2;
            } else if (nextChar == '/') {
                rollOne = Character.getNumericValue(currentChar);
                rollTwo = 10 - rollOne;
                currentFrame.set(rollOne, rollTwo);
                stringIndexNextFrame = 3;
            } else {
                if (currentChar != '-') {
                    rollOne = Character.getNumericValue(currentChar);
                }
                if (nextChar != '-') {
                    rollTwo = Character.getNumericValue(nextChar);
                }
                stringIndexNextFrame = 3;
            }
            currentFrame.set(rollOne, rollTwo);
            framesOut[index] = currentFrame;
            stringIndexCurrentFrame += stringIndexNextFrame;
        }
        currentFrame = new BowlingFrame();

        currentChar = rolls.charAt(stringIndexCurrentFrame);
        System.out.println("currentChar: " + currentChar);
        nextChar = rolls.charAt(stringIndexCurrentFrame + 1);
        if (currentChar == 'X') {
            rollOne = 10;
            rollTwo = 0;
            char finalChar;
            finalChar = rolls.charAt(stringIndexCurrentFrame + 2);
            extraRollOne = Character.getNumericValue(nextChar);
            extraRollTwo = Character.getNumericValue(finalChar);
            System.out.println("rollOne: " + rollOne);
            System.out.println("rollTwo: " + rollTwo);
            System.out.println("extraRollOne: " + extraRollOne);
            System.out.println("extraRollTwo: " + extraRollTwo);
            currentFrame.set(rollOne, rollTwo, extraRollOne, extraRollTwo);
        } else if (nextChar == '/') {
            rollOne = currentChar;
            rollTwo = 10 - rollOne;
            extraRollOne = Character.getNumericValue(nextChar);
            currentFrame.set(rollOne, rollTwo, extraRollOne);
        } else {
            rollOne = 0;
            rollTwo = 0;
            if (currentChar != '-') {
                rollOne = Character.getNumericValue(currentChar);
            }
            if (nextChar != '-') {
                rollTwo = Character.getNumericValue(nextChar);
            }
            currentFrame.set(rollOne, rollTwo);
        }
        framesOut[9] = currentFrame;
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

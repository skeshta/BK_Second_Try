public class BowlingGame {
    private BowlingFrame[] frames;
    private int numberOfFrames;

    public int getScore(BowlingFrame[] framesIn) {
        int score = 0;
        int currentScore;
        frames = framesIn;
        numberOfFrames = frames.length;
        BowlingFrame currentFrame;
        for (int count = 0; count < numberOfFrames; count++) {
            currentFrame = frames[count];
            currentScore = currentFrame.sumRolls() + bonusScore(count);
            score += currentScore;
        }
        return score;
    }

    private int bonusScore(int index) {
        int bonus;
        if (index < numberOfFrames - 1) {
            bonus = bonusScoreOngoing(index);
        } else {
            bonus = bonusScoreFinal(index);
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
                if (index < numberOfFrames - 3) {
                    bonus += frames[index + 2].getRollOne();
                } else {
                    bonus += nextFrame.getBonusRollOne();
                }
            }
        }
        return bonus;
    }

    private int bonusScoreFinal(int index) {
        int bonus = 0;
        BowlingFrame currentFrame = frames[index];
        if (currentFrame.isSpare()) {
            bonus = currentFrame.getBonusRoll();
        } else if (currentFrame.isStrike()) {
            bonus = currentFrame.getBonusRoll();
        }
        return bonus;
    }

}

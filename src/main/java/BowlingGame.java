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
            /*
            Fun fact:
            "currentFrame.sumRolls() + bonusScore(index)" makes it look
                    like bonusScore should be a method of the *frame* rather than the game.
            But the frame only records what we rolled. The frame knows the rolls, not the rules.
            The game is what tells us how to calculate our score. The game knows both rolls and rules.

            Although that is probably obvious to you.
            */
        }
        return totalScore;
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
                if (index < finalFrameIndex - 2) {
                    bonus += frames[index + 2].getRollOne();
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

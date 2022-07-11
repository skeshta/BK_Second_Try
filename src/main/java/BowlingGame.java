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
    /*
        Fun fact:
        "currentFrame.sumRolls() + bonusScore(index)" makes it look
                like bonusScore should be a method of the *frame* rather than the game.
        But the frame only records what we rolled. The frame knows the rolls, not the rules.
        The game is what tells us how to calculate our score. The game knows both rolls and rules.

        P.S.:
        If the frame does not know the rules (only the rolls), then we could ask:
        Why can a frame tell whether it is a spare/strike?
        Why are isSpare and isStrike methods of the frame, not the game?

        In terms of the programming it seems cleaner this way (and makes the code more readable).
        But in terms of the abstraction, it seems inconsistent with my point above.

        P.P.S.:
        It occurs to me that this class mostly handles the scoring.
        Should it be renamed to something like "BowlingScore"?
    */

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

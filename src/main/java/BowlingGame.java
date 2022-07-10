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
        int bonus = 0;
        if (index < numberOfFrames - 1) {
            BowlingFrame currentFrame;
            BowlingFrame nextFrame;
            currentFrame = frames[index];
            nextFrame = frames[index + 1];
            if (currentFrame.isSpare()) {
                bonus = nextFrame.getRollOne();
            }
        }
        return bonus;
    }

}

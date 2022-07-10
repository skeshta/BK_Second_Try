public class BowlingGame {
    private BowlingFrame[] frames;

    public int getScore(BowlingFrame[] framesIn) {
        int score = 0;
        int numberOfFrames;
        int currentScore;
        frames = framesIn;
        BowlingFrame currentFrame = new BowlingFrame();
        numberOfFrames = frames.length;
        for (int count = 0; count < numberOfFrames; count++) {
            currentFrame = frames[count];
            currentScore = currentFrame.sumRolls() + bonusScore(count);
            score += currentScore;
        }
        return score;
    }

    private int bonusScore(int index) {
        int bonus = 0;
        BowlingFrame currentFrame = new BowlingFrame();
        BowlingFrame nextFrame = new BowlingFrame();
        currentFrame = frames[index];
        nextFrame = frames[index + 1];
        if (currentFrame.isSpare()) {
            bonus = nextFrame.getRollOne();
        }
        return bonus;
    }

}

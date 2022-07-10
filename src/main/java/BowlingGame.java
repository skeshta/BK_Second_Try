public class BowlingGame {

    public int getScore(BowlingFrame[] frames) {
        int score = 0;
        int numberOfFrames;
        int currentScore;
        numberOfFrames = frames.length;
        for (int count = 0; count < numberOfFrames; count++) {
            currentScore = frames[count].sumRolls();
            //currentScore += bonusScore;
            score += currentScore;
        }
        return score;
    }

}

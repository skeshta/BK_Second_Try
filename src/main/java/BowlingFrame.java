public class BowlingFrame {

    private final int error_value = -2147483648;
    private int rollOne;
    private int rollTwo;

    public void setFrame(int firstRoll, int secondRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
    }

    public int getRollOne() {
        return rollOne;
    }

    public int getRollTwo() {
        return rollTwo;
    }

    private boolean frameValid() {
        boolean bothRollsOK;
        bothRollsOK = rollOne + rollTwo <= 10 && Math.min(rollOne, rollTwo) >= 0;
        return bothRollsOK;
    }

    public boolean isAStrike() {
        boolean strike;
        strike = (rollOne == 10 && rollTwo == 0);
        return strike;
    }

    public boolean isASpare() {
        boolean spare;
        spare = frameSum() == 10 && rollOne != 10;
        return spare;
    }

    public boolean isRegular() {
        boolean regular;
        regular = !(isAStrike() || isASpare());
        return regular;
    }

    public int frameSum() {
        int score;
        if (!frameValid()) {
            return error_value;
        }
        else {
            score = rollOne + rollTwo;
            return score;
        }
    }
}

public class BowlingFrame {
    private int rollOne;
    private int rollTwo;
    private int extraRollOne = 0;
    private int extraRollTwo = 0;

    public void set(int firstRoll, int secondRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
    }

    public void set(int firstRoll, int secondRoll, int firstExtraRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
        extraRollOne = firstExtraRoll;
    }

    public void set(int firstRoll, int secondRoll, int firstExtraRoll, int secondExtraRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
        extraRollOne = firstExtraRoll;
        extraRollTwo = secondExtraRoll;
    }

    public int sumRolls() {
        int sum = rollOne + rollTwo;
        return sum;
    }

    public int getRollOne() {
        return rollOne;
    }

    public int getBonusRoll() {
        return extraRollOne + extraRollTwo;
    }

    public int getExtraRollOne() {
        return extraRollOne;
    }

    public boolean isSpare() {
        boolean isSpareOrNot;
        isSpareOrNot = (sumRolls() == 10 && rollOne != 10);
        return isSpareOrNot;
    }

    public boolean isStrike() {
        boolean isStrikeOrNot;
        isStrikeOrNot = (rollOne == 10 && rollTwo == 0);
        return isStrikeOrNot;
    }
}

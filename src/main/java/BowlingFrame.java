public class BowlingFrame {
    private int rollOne;
    private int rollTwo;
    private int rollThree = 0;
    private int maxPins;

    public void setRolls(int firstRoll, int secondRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
    }

    public void setRolls(int firstRoll, int secondRoll, int thirdRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
        rollThree = thirdRoll;
    }

    public void setMaxPins(int maximumPins) {
        maxPins = maximumPins;
    }

    public int sumRollsOneAndTwo() {
        return rollOne + rollTwo;
    }

    public int getRollOne() {
        return rollOne;
    }

    public int getRollThree() {
        return rollThree;
    }

    public int sumRollsTwoAndThree() {
        return rollTwo + rollThree;
    }

    public boolean isSpare() {
        boolean isSpareOrNot;
        isSpareOrNot = (sumRollsOneAndTwo() == maxPins && rollOne != maxPins);
        return isSpareOrNot;
    }

    public boolean isStrike() {
        boolean isStrikeOrNot;
        isStrikeOrNot = (rollOne == maxPins);
        return isStrikeOrNot;
    }
}

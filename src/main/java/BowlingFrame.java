public class BowlingFrame {
    /*
    Ultimately, this is just a fancy array/tuple. Should it be a class?
    I suppose having a dedicated class improves readability.
    "frame.sumRolls" is more obvious than a for-loop.
    But then again, there may be a built-in way to sum arrays, or a datatype that's better suited to the purpose.
     */
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
        // "rollTwo == 0" is unnecessary if we assume good input.
        isStrikeOrNot = (rollOne == 10 && rollTwo == 0);
        return isStrikeOrNot;
    }
}

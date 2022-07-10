public class BowlingFrame {
    /*
    Ultimately, this is just a fancy array/tuple. Should it be a class?
    I suppose having a dedicated class improves readability.
    "frame.sumRolls" is more obvious than a for-loop.
    But then again, there may be a built-in way to sum arrays, or a datatype that's better suited to the purpose.
     */
    private int rollOne;
    private int rollTwo;
    private int bonusRollOne = 0;
    private int bonusRollTwo = 0;

    public void set(int firstRoll, int secondRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
    }

    public void set(int firstRoll, int secondRoll, int firstBonusRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
        bonusRollOne = firstBonusRoll;
    }

    public void set(int firstRoll, int secondRoll, int firstBonusRoll, int secondBonusRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
        bonusRollOne = firstBonusRoll;
        bonusRollTwo = secondBonusRoll;
    }

    public int sumRolls() {
        int sum = rollOne + rollTwo;
        return sum;
    }

    public int getRollOne() {
        return rollOne;
    }

    public int getBonusRoll() {
        int bonus = 0;
        if (isSpare()) {
            bonus = bonusRollOne;
        }
        return bonus;
    }

    public boolean isSpare() {
        boolean isSpareOrNot;
        isSpareOrNot = (sumRolls() == 10 && rollOne != 10);
        return isSpareOrNot;
    }
}

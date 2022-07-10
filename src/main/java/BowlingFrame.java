public class BowlingFrame {
    /*
    Ultimately, this is just a fancy array/tuple. Should it be a class?
    I suppose having a dedicated class improves readability.
    "frame.sumRolls" is more obvious than a for-loop.
    But then again, there may be a built-in way to sum arrays, or a datatype that's better suited to the purpose.
     */
    private int rollOne;
    private int rollTwo;

    public void set(int firstRoll, int secondRoll) {
        rollOne = firstRoll;
        rollTwo = secondRoll;
    }

    public int sumRolls() {
        int sum = rollOne + rollTwo;
        return sum;
    }
}

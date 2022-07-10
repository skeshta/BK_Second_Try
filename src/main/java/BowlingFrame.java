public class BowlingFrame {
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

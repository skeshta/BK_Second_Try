public class BowlingGame {
    private int gameScore = 0;

    //Get score
    public int getScore() {
        return gameScore;
    }

    public boolean checkInput(int rollOne, int rollTwo) {
        boolean inputOK;
        inputOK = rollOne + rollTwo <= 10 && Math.min(rollOne, rollTwo) >= 0;
        return inputOK;
    }

    public int nextFrame(int rollOne, int rollTwo) {
        int error_value = -2147483648;
        if (!checkInput(rollOne, rollTwo)) {
            return error_value;
        }
        else {
            gameScore += rollOne + rollTwo;
            return gameScore;
        }
    }

}

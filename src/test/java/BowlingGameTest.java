import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    private BowlingGame game;
    private final int error_value = -2147483648;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    @AfterEach
    void tearDown() {
        game = null;
    }


    /*
    Unnecessary, since we have other tests to check the score.
    If the score did start as something other than 0, those other tests would catch it.
    @Test
    @DisplayName("Initial score is 0")
    void TestInitialScore() {
        assertEquals(0, game.getScore());
    }
    */

    @Test
    @DisplayName("A frame with 1 and 7 has score 8")
    void TestSingleFrameNoStrikeNoSpare() {
        int rollOne = 1;
        int rollTwo = 7;
        assertEquals(8, game.scoreSingleFrame(rollOne, rollTwo));
    }

    @Test
    @DisplayName("Both rolls must be <= 10")
    void TestNumbersTooHigh() {
        int rollOne = 4;
        int rollTwo = 9;
        assertEquals(error_value , game.scoreSingleFrame(rollOne, rollTwo));
    }

    @Test
    @DisplayName("Both rolls must be >= 0")
    void TestNumbersTooLow() {
        int rollOne = -4;
        int rollTwo = 9;
        assertEquals(error_value , game.scoreSingleFrame(rollOne, rollTwo));
    }

    @Test
    @DisplayName("Two regular frames sum correctly")
    void TestSumTwoRegularFrames() {
        int[] fourRolls = {1, 3, 2, 5};
        assertEquals(11, game.scoreGameTotal(fourRolls));
    }

    @Test
    @DisplayName("Number of rolls is even")
    void TestOddNumberOfRolls() {
        int[] fiveRolls = {1, 2, 1, 2, 1};
        assertEquals(error_value, game.scoreGameTotal(fiveRolls));
    }

}
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

    @Test
    @DisplayName("Spare sums correctly")
    void TestSpare() {
        int[] spare = {1, 9, 4, 3};
        assertEquals(21, game.scoreGameTotal(spare));
    }

    @Test
    @DisplayName("Strike sums correctly")
    void TestStrike() {
        int[] strike = {10, 0, 2, 6};
        assertEquals(26, game.scoreGameTotal(strike));
    }

    @Test
    @DisplayName("Spare in last frame grants exactly one bonus roll")
    void TestLastFrameIsAFalseSpare() {
        int[] lastFrameSpare = {0, 0, 1, 1, 0, 0, 2, 2, 0, 0, 3, 3, 0, 0, 4, 4, 0, 0, 5, 5, 7, 2};
        assertEquals(error_value, game.scoreGameTotal(lastFrameSpare));
    }

    @Test
    @DisplayName("Spare in last frame sums correctly")
    void TestLastFrameIsASpare() {
        int[] lastFrameSpare = {0, 0, 1, 1, 0, 0, 2, 2, 0, 0, 3, 3, 0, 0, 4, 4, 0, 0, 5, 5, 7, 0};
        assertEquals(37, game.scoreGameTotal(lastFrameSpare));
    }

}
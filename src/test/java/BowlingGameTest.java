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
    @DisplayName("Two regular frames sum correctly")
    void TestSumTwoRegularFrames() {
        int[] fourRolls = {1, 3, 2, 5};
        game.setGame(fourRolls);
        assertEquals(11, game.score());
    }

    @Test
    @DisplayName("Number of rolls is even")
    void TestOddNumberOfRolls() {
        int[] fiveRolls = {1, 2, 1, 2, 1};
        game.setGame(fiveRolls);
        assertEquals(error_value, game.score());
    }

    @Test
    @DisplayName("Spare sums correctly")
    void TestSpare() {
        int[] spare = {1, 9, 4, 3};
        game.setGame(spare);
        assertEquals(21, game.score());
    }

    @Test
    @DisplayName("Strike sums correctly")
    void TestStrike() {
        int[] strike = {10, 0, 2, 6};
        game.setGame(strike);
        assertEquals(26, game.score());
    }

    @Test
    @DisplayName("Spare in last frame grants exactly one bonus roll")
    void TestLastFrameIsAFalseSpare() {
        int[] lastFrameSpare = {0, 0, 1, 1, 0, 0, 2, 2, 0, 0, 3, 3, 0, 0, 4, 4, 0, 0, 5, 5, 7, 2};
        game.setGame(lastFrameSpare);
        assertEquals(error_value, game.score());
    }

    @Test
    @DisplayName("Spare in last frame sums correctly")
    void TestLastFrameIsASpare() {
        int[] lastFrameSpare = {0, 0, 1, 1, 0, 0, 2, 2, 0, 0, 3, 3, 0, 0, 4, 4, 0, 0, 5, 5, 7, 0};
        game.setGame(lastFrameSpare);
        assertEquals(37, game.score());
    }

    @Test
    @DisplayName("10 frames sum correctly")
    void TestFullGame() {
        int[] lastFrameSpare = {10, 0, 0, 10, 0, 3, 2, 2, 0, 0, 3, 3, 10, 0, 4, 6, 7, 0, 10, 0, 7, 3};
        game.setGame(lastFrameSpare);
        assertEquals(107, game.score());
    }

    @Test
    @DisplayName("All zeroes sums to 0")
    void TestAllZeroes() {
        int[] allZeroes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        game.setGame(allZeroes);
        assertEquals(0, game.score());
    }

    @Test
    @DisplayName("Two strikes sum correctly")
    void TestTwoStrikes() {
        int[] twoStrikes = {10, 0, 10, 0, 1, 4};
        game.setGame(twoStrikes);
        assertEquals(41, game.score());
    }

    /*
    @Test
    @DisplayName("All strikes sums to 300")
    void TestAllStrikes() {
        int[] allStrikes = {10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 10};
        assertEquals(300, game.scoreGameTotal(allStrikes));
    }
    */

}
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    private BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    @DisplayName("Two regular frames")
    void TestSum2RegularFrames() {
        String rolls = "25 36 -- -- -- -- -- -- -- --";
        assertEquals(16, game.getScore(rolls));
    }

    @Test
    @DisplayName("Ten regular frames")
    void TestSum10RegularFrames () {
        String rolls = "23 23 23 23 23 23 23 23 23 23";
        assertEquals(50, game.getScore(rolls));
    }

    @Test
    @DisplayName("Ten frames with 0 points each")
    void Test10ZeroFrames() {
        String rolls = "-- -- -- -- -- -- -- -- -- --";
        assertEquals(0, game.getScore(rolls));
    }

    /*
    As above, this test sums only 2 frames.
    Probably we should add a test with 10 frames and 1 spare.
    Analogously for the strikes.
    */
    @Test
    @DisplayName("A spare and a regular frame")
    void TestSparePlusRegular () {
        String rolls = "1/ 23 -- -- -- -- -- -- -- --";
        assertEquals(17, game.getScore(rolls));
    }

    @Test
    @DisplayName("A strike and a regular frame")
    void TestStrikePlusRegular() {
        String rolls = "X 23 -- -- -- -- -- -- -- --";
        assertEquals(20, game.getScore(rolls));
    }

    @Test
    @DisplayName("Ten strikes with perfect bonus rolls")
    void TestSum10Strikes() {
        String rolls = "X X X X X X X X X XXX";
        assertEquals(300, game.getScore(rolls));
    }

    @Test
    @DisplayName("All frames are spares")
    void TestAllFramesAreSpares() {
        String rolls = "1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/1";
        assertEquals(110, game.getScore(rolls));
    }

    @Test
    @DisplayName("Two strikes in a row")
    void TestTwoStrikesInARow() {
        String rolls = "X X 1- -- -- -- -- -- -- --";
        assertEquals(33, game.getScore(rolls));
    }

    void TestFrameValues(BowlingFrame testFrame, int realRollOne, int realRollSum) {
        int testRollOne;
        int testRollSum;
        testRollOne = testFrame.getRollOne();
        testRollSum = testFrame.sumRolls();
        assertEquals(realRollOne, testRollOne);
        assertEquals(realRollSum, testRollSum);
    }

    void TestFrameValues(BowlingFrame testFrame, int realRollOne, int realRollSum, int realExtraRollOne, int realBonusRoll) {
        int testRollOne;
        int testRollSum;
        int testExtraRollOne = testFrame.getExtraRollOne();
        int testBonusRoll = testFrame.getBonusRoll();
        testRollOne = testFrame.getRollOne();
        testRollSum = testFrame.sumRolls();
        assertEquals(realRollOne, testRollOne);
        assertEquals(realRollSum, testRollSum);
        assertEquals(realExtraRollOne, testExtraRollOne);
        assertEquals(realBonusRoll, testBonusRoll);
    }

    @Test
    @DisplayName("Convert string of rolls to frame objects")
    void TestStringToFrames() {
        String rolls = "-- 11 22 33 44 5/ 6/ 7/ 8/ X42";
        BowlingFrame[] convertedRolls = game.stringToFrames(rolls);
        for (int index = 0; index < 5; index++) {
            TestFrameValues(convertedRolls[index], index, index * 2);
        }
        for (int index = 5; index < 9; index++) {
            TestFrameValues(convertedRolls[index], index, 10);
        }
        TestFrameValues(convertedRolls[9], 10, 10, 4, 6);
    }

    @Test
    @DisplayName("Convert string of rolls to frame objects")
    void TestStringToFramesAllZeroes() {
        String rolls = "-- -- -- -- -- -- -- -- -- --";
        BowlingFrame[] convertedRolls = game.stringToFrames(rolls);
        for (int index = 0; index < 10; index++) {
            TestFrameValues(convertedRolls[index], 0, 0);
        }
    }

    @Test
    @DisplayName("stringToFrame with a -/ spare")
    void TestStringToFrameSpare() {
        String rolls = "-/ -- -- -- -- -- -- -- -- --";
        assertEquals(10, game.getScore(rolls));
    }

}
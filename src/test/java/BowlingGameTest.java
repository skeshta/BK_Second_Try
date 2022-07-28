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
        assertEquals(16, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("Ten regular frames")
    void TestSum10RegularFrames () {
        String rolls = "23 23 23 23 23 23 23 23 23 23";
        assertEquals(50, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("Ten frames with 0 points each")
    void Test10ZeroFrames() {
        String rolls = "-- -- -- -- -- -- -- -- -- --";
        assertEquals(0, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("A spare and a regular frame")
    void TestSparePlusRegular () {
        String rolls = "1/ 23 -- -- -- -- -- -- -- --";
        assertEquals(17, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("A strike and a regular frame")
    void TestStrikePlusRegular() {
        String rolls = "X 23 -- -- -- -- -- -- -- --";
        assertEquals(20, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("Ten strikes with perfect bonus rolls")
    void TestSum10Strikes() {
        String rolls = "X X X X X X X X X XXX";
        assertEquals(300, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("All frames are spares")
    void TestAllFramesAreSpares() {
        String rolls = "1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/1";
        assertEquals(110, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("Two strikes in a row")
    void TestTwoStrikesInARow() {
        String rolls = "X X 1- -- -- -- -- -- -- --";
        assertEquals(33, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("stringToFrame with a -/ spare")
    void TestStringToFrameSpare() {
        String rolls = "-/ -- -- -- -- -- -- -- -- --";
        assertEquals(10, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("A strike and a spare in a row")
    void TestStrikeAndSpare() {
        String rolls = "X 1/ -- -- -- -- -- -- -- --";
        assertEquals(30, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("A strike and a miss in a row")
    void TestStrikeAndMiss() {
        String rolls = "X -1 -- -- -- -- -- -- -- -- --";
        assertEquals(12, game.getGameScore(rolls));
    }

    @Test
    @DisplayName("A spare and a miss in a row")
    void TestSpareAndMiss() {
        String rolls = "3/ -1 -- -- -- -- -- -- -- -- --";
        assertEquals(11, game.getGameScore(rolls));
    }

    void TestFrameValues(BowlingFrame testFrame, int realRollOne, int realRollSum) {
        int testRollOne;
        int testRollSum;
        testRollOne = testFrame.getRollOne();
        testRollSum = testFrame.getRollOne() + testFrame.getRollTwo();
        assertEquals(realRollOne, testRollOne);
        assertEquals(realRollSum, testRollSum);
    }

    void TestFrameValues(BowlingFrame testFrame, int realRollOne, int realSumRollOneAndTwo, int realRollThree, int realSumRollTwoAndThree) {
        int testRollOne;
        int testSumRollOneAndTwo;
        int testRollThree = testFrame.getRollThree();
        int testSumRollTwoAndThree = testFrame.getRollTwo() + testFrame.getRollThree();
        testRollOne = testFrame.getRollOne();
        testSumRollOneAndTwo = testFrame.getRollOne() + testFrame.getRollTwo();
        assertEquals(realRollOne, testRollOne);
        assertEquals(realSumRollOneAndTwo, testSumRollOneAndTwo);
        assertEquals(realRollThree, testRollThree);
        assertEquals(realSumRollTwoAndThree, testSumRollTwoAndThree);
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
        TestFrameValues(convertedRolls[9], 10, 14, 2, 6);
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

}
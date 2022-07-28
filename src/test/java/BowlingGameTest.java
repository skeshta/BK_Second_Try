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
        game.setup(10, 10, 'X', '/', '-');
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    @DisplayName("Two regular frames")
    void TestSum2RegularFrames() {
        String rolls = "25 36 -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(16, game.getGameScore());
    }

    @Test
    @DisplayName("Ten regular frames")
    void TestSum10RegularFrames () {
        String rolls = "23 23 23 23 23 23 23 23 23 23";
        game.setFrames(rolls);
        assertEquals(50, game.getGameScore());
    }

    @Test
    @DisplayName("Ten frames with 0 points each")
    void Test10ZeroFrames() {
        String rolls = "-- -- -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(0, game.getGameScore());
    }

    @Test
    @DisplayName("A spare and a regular frame")
    void TestSparePlusRegular () {
        String rolls = "1/ 23 -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(17, game.getGameScore());
    }

    @Test
    @DisplayName("A strike and a regular frame")
    void TestStrikePlusRegular() {
        String rolls = "X 23 -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(20, game.getGameScore());
    }

    @Test
    @DisplayName("Ten strikes with perfect bonus rolls")
    void TestSum10Strikes() {
        String rolls = "X X X X X X X X X XXX";
        game.setFrames(rolls);
        assertEquals(300, game.getGameScore());
    }

    @Test
    @DisplayName("All frames are spares")
    void TestAllFramesAreSpares() {
        String rolls = "1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/1";
        game.setFrames(rolls);
        assertEquals(110, game.getGameScore());
    }

    @Test
    @DisplayName("Two strikes in a row")
    void TestTwoStrikesInARow() {
        String rolls = "X X 1- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(33, game.getGameScore());
    }

    @Test
    @DisplayName("stringToFrame with a -/ spare")
    void TestStringToFrameSpare() {
        String rolls = "-/ -- -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(10, game.getGameScore());
    }

    @Test
    @DisplayName("A strike and a spare in a row")
    void TestStrikeAndSpare() {
        String rolls = "X 1/ -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(30, game.getGameScore());
    }

    @Test
    @DisplayName("A strike and a miss in a row")
    void TestStrikeAndMiss() {
        String rolls = "X -1 -- -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(12, game.getGameScore());
    }

    @Test
    @DisplayName("A spare and a miss in a row")
    void TestSpareAndMiss() {
        String rolls = "3/ -1 -- -- -- -- -- -- -- -- --";
        game.setFrames(rolls);
        assertEquals(11, game.getGameScore());
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
        game.setFrames(rolls);
        BowlingFrame[] convertedRolls = game.getFrames();
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
        game.setFrames(rolls);
        BowlingFrame[] convertedRolls = game.getFrames();
        for (int index = 0; index < 10; index++) {
            TestFrameValues(convertedRolls[index], 0, 0);
        }
    }

}
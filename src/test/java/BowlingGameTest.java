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

    /*
    This test, to sum 2 regular frames, seems redundant since
        (1) we only want to score a complete game.
        (2) We have a test that sums 10 regular frames.
    */
    @Test
    @DisplayName("Two regular frames")
    void TestSum2RegularFrames() {
        BowlingFrame[] frames = new BowlingFrame[2];
        frames[0] = new BowlingFrame();
        frames[1] = new BowlingFrame();
        frames[0].set(2, 5);
        frames[1].set(3, 6);
        assertEquals(16, game.getScore(frames));
    }

    @Test
    @DisplayName("Ten regular frames")
    void TestSum10RegularFrames () {
        int rollOne = 2;
        int rollTwo = 3;
        BowlingFrame[] frames = new BowlingFrame[10];
        BowlingFrame regularFrame = new BowlingFrame();
        regularFrame.set(rollOne, rollTwo);
        for (int index = 0; index < 10; index++) {
            frames[index] = regularFrame;
        }
        assertEquals(50, game.getScore(frames));
    }

    /*
    This test, to sum 10 frames with 0 points each, seems very obvious.
    But I maybe it should stay as an edge case.
    */
    @Test
    @DisplayName("Ten frames with 0 points each")
    void Test10ZeroFrames() {
        BowlingFrame[] frames = new BowlingFrame[10];
        BowlingFrame regularFrame = new BowlingFrame();
        regularFrame.set(0, 0);
        for (int index = 0; index < 10; index++) {
            frames[index] = regularFrame;
        }
        assertEquals(0, game.getScore(frames));
    }

    /*
    As above, this test sums only 2 frames.
    Probably we should add a test with 10 frames and 1 spare.
    Analogously for the strikes.
    */
    @Test
    @DisplayName("A spare and a regular frame")
    void TestSparePlusRegular () {
        BowlingFrame[] frames = new BowlingFrame[2];
        BowlingFrame spare = new BowlingFrame();
        BowlingFrame regularFrame = new BowlingFrame();
        spare.set(1, 9);
        regularFrame.set(2, 3);
        frames[0] = spare;
        frames[1] = regularFrame;
        assertEquals(17, game.getScore(frames));
    }

    @Test
    @DisplayName("A strike and a regular frame")
    void TestStrikePlusRegular() {
        BowlingFrame[] frames = new BowlingFrame[2];
        BowlingFrame strike = new BowlingFrame();
        BowlingFrame regularFrame = new BowlingFrame();
        strike.set(10, 0);
        regularFrame.set(2, 3);
        frames[0] = strike;
        frames[1] = regularFrame;
        assertEquals(20, game.getScore(frames));
    }

    @Test
    @DisplayName("Ten strikes with perfect bonus rolls")
    void TestSum10Strikes() {
        int rollOne = 10;
        int rollTwo = 0;
        BowlingFrame[] frames = new BowlingFrame[10];
        BowlingFrame regularFrame = new BowlingFrame();
        regularFrame.set(rollOne, rollTwo);
        for (int index = 0; index < 9; index++) {
            frames[index] = regularFrame;
        }
        regularFrame.set(rollOne, rollTwo, 10, 10);
        frames[9] = regularFrame;
        assertEquals(300, game.getScore(frames));
    }

    @Test
    @DisplayName("All frames are spares")
    void TestAllFramesAreSpares() {
        int rollOne = 1;
        int rollTwo = 9;
        BowlingFrame[] frames = new BowlingFrame[10];
        BowlingFrame regularFrame = new BowlingFrame();
        regularFrame.set(rollOne, rollTwo);
        for (int index = 0; index < 9; index++) {
            frames[index] = regularFrame;
        }
        regularFrame.set(rollOne, rollTwo, 1);
        frames[9] = regularFrame;
        assertEquals(110, game.getScore(frames));
    }

    @Test
    @DisplayName("Frame has correct values")
    void TestFrameValues(BowlingFrame testFrame, int realRollOne, int realRollSum) {
        int testRollOne;
        int testRollSum;
        testRollOne = testFrame.getRollOne();
        testRollSum = testFrame.sumRolls();
        assertEquals(realRollOne, testRollOne);
        assertEquals(realRollSum, testRollSum);
    }

    @Test
    @DisplayName("Frame has correct values")
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
        String rolls = "-- 11 22 33 44 55 6/ 7/ 8/ 9/ X42";
        BowlingFrame[] convertedRolls = game.stringToFrames(rolls);
        for (int index = 0; index < 6; index++) {
            TestFrameValues(convertedRolls[index], index, index * 2);
        }
        for (int index = 6; index < 9; index++) {
            TestFrameValues(convertedRolls[index], index, 10);
        }
        TestFrameValues(convertedRolls[9], 10, 10, 4, 6);
    }

    @Test
    @DisplayName("Convert string of rolls to frame objects")
    void TestStringToFramesAllZeroes() {
        String rolls = "-- -- -- -- -- -- -- -- -- -- --";
        BowlingFrame[] convertedRolls = game.stringToFrames(rolls);
        for (int index = 0; index < 10; index++) {
            TestFrameValues(convertedRolls[index], 0, 0);
        }
    }

}
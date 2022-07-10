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
    @Test
    @DisplayName("Initial score is 0")
    void TestInitialScore() {
        assertEquals(0, game.getScore());
    }
    */

    @Test
    @DisplayName("Two regular frames sum correctly")
    void TestSum2RegularFrames() {
        BowlingFrame[] frames = new BowlingFrame[2];
        frames[0] = new BowlingFrame();
        frames[1] = new BowlingFrame();
        frames[0].set(2, 5);
        frames[1].set(3, 6);
        assertEquals(16, game.getScore(frames));
    }

    @Test
    @DisplayName("Ten regular frames sum correctly")
    void TestSum10RegularFrames () {
        int rollOne = 2;
        int rollTwo = 3;
        BowlingFrame[] frames = new BowlingFrame[10];
        BowlingFrame regularFrame = new BowlingFrame();
        regularFrame.set(rollOne, rollTwo);
        for (int count = 0; count < 10; count++) {
            frames[count] = regularFrame;
        }
        assertEquals(50, game.getScore(frames));
    }

    @Test
    @DisplayName("Spare sums correctly")
    void TestSpareSumsCorrectly () {
        BowlingFrame[] frames = new BowlingFrame[2];
        BowlingFrame spare = new BowlingFrame();
        BowlingFrame regularFrame = new BowlingFrame();
        spare.set(1, 9);
        regularFrame.set(2, 3);
        frames[0] = spare;
        frames[1] = regularFrame;
        assertEquals(17, game.getScore(frames));
    }
}
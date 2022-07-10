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
    void TestSumTwoRegularFrames() {
        BowlingFrame[] frames = new BowlingFrame[2];
        frames[0] = new BowlingFrame();
        frames[1] = new BowlingFrame();
        frames[0].set(2, 5);
        frames[1].set(3, 6);
        assertEquals(16, game.getScore(frames));
    }
}
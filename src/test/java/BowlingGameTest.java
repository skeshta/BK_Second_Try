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
    @DisplayName("Initial score is 0")
    void TestInitialScore() {
        assertEquals(0, game.getScore());
    }

    @Test
    @DisplayName("A frame with 1 and 7 has score 8")
    void TestSingleFrameNoStrikeNoSpare() {
        int rollOne = 1;
        int rollTwo = 7;
        assertEquals(8, game.nextFrame(rollOne, rollTwo));
    }
}
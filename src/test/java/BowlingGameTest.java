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
    @DisplayName("Sum two regular frames")
    void TestSum2RegularFrames() {
        BowlingFrame[] frames = new BowlingFrame[2];
        frames[0] = new BowlingFrame();
        frames[1] = new BowlingFrame();
        frames[0].set(2, 5);
        frames[1].set(3, 6);
        assertEquals(16, game.getScore(frames));
    }

    @Test
    @DisplayName("Sum ten regular frames")
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
    @DisplayName("Sum a spare and a regular frame")
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
    @DisplayName("Sum a strike and a regular frame")
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
    @DisplayName("Sum ten strikes with perfect bonus rolls")
    void TestSum10Strikes() {
        int rollOne = 10;
        int rollTwo = 0;
        BowlingFrame[] frames = new BowlingFrame[10];
        BowlingFrame regularFrame = new BowlingFrame();
        regularFrame.set(rollOne, rollTwo);
        for (int count = 0; count < 10; count++) {
            frames[count] = regularFrame;
        }
        assertEquals(300, game.getScore(frames));
    }
}
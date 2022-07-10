import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingFrameTest {
    private BowlingFrame frame;

    @BeforeEach
    void setUp() {
        frame = new BowlingFrame();
    }

    @AfterEach
    void tearDown() {
        frame = null;
    }

    @Test
    @DisplayName("Sum both rolls")
    void TestSumRolls() {
        frame.set(1, 4);
        assertEquals(5, frame.sumRolls());
    }

    @Test
    @DisplayName("Extract roll 1")
    void TestRollOne() {
        frame.set(7, 0);
        assertEquals(7, frame.getRollOne());
    }

    /*
    Naming needs work. The frame does not know whether it is the final frame.
    And in theory, any frame could receive a bonus roll.
    */
    @Test
    @DisplayName("Bonus rolls default to 0")
    void TestBonusRollRegular() {
        frame.set(2, 3);
        assertEquals(0, frame.getBonusRoll());
    }

    @Test
    @DisplayName("Spare final frame has 1 bonus roll")
    void TestBonusRollSpare() {
        frame.set(1, 9, 5);
        assertEquals(5, frame.getBonusRoll());
    }

    @Test
    @DisplayName("Strike final frame has 2 bonus rolls")
    void TestBonusRollStrike() {
        frame.set(10, 0, 2, 3);
        assertEquals(5, frame.getBonusRoll());
    }

    @Test
    @DisplayName("Identify all spares")
    void TestIsSpare() {
        for (int count = 0; count < 10; count++) {
            frame.set(count, 10 - count);
            assertEquals(true, frame.isSpare());
        }
    }

    @Test
    @DisplayName("Identify only spares")
    void TestIsNotSpare() {
        frame.set(10, 0);
        assertEquals(false, frame.isSpare());
        frame.set(2, 3);
        assertEquals(false, frame.isSpare());
    }

    @Test
    @DisplayName("Identify a strike")
    void TestIsStrike() {
        frame.set(10, 0);
        assertEquals(true, frame.isStrike());
    }

}
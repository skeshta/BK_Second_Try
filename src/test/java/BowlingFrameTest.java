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

    @Test
    @DisplayName("Bonus rolls default to 0")
    void TestBonusRollRegular() {

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
    @DisplayName("All spares are identified")
    void TestIsSpare() {
        for (int count = 0; count < 10; count++) {
            frame.set(count, 10 - count);
            assertEquals(true, frame.isSpare());
        }
    }

}
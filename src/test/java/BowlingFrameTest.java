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
    void TestGetRollOne() {
        frame.set(7, 0);
        assertEquals(7, frame.getRollOne());
    }

    @Test
    @DisplayName("Extract bonus roll 1")
    void TestGetBonusRollOne() {
        frame.set(10, 0, 4);
        assertEquals(4, frame.getBonusRollOne());
    }

    @Test
    @DisplayName("Bonus rolls default to 0")
    void Test0BonusRolls() {
        frame.set(2, 3);
        assertEquals(0, frame.getBonusRoll());
    }

    @Test
    @DisplayName("Can set one bonus roll")
    void Test1BonusRoll() {
        frame.set(1, 9, 5);
        assertEquals(5, frame.getBonusRoll());
    }

    @Test
    @DisplayName("Can set two bonus rolls")
    void Test2BonusRolls() {
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
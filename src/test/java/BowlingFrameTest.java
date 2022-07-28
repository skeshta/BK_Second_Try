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
        frame.setMaxPins(10);
    }

    @AfterEach
    void tearDown() {
        frame = null;
    }

    @Test
    @DisplayName("Sum both rolls")
    void TestSumRolls() {
        frame.setRolls(1, 4);
        assertEquals(5, frame.sumRollsOneAndTwo());
    }

    @Test
    @DisplayName("Extract roll 1")
    void TestGetRollOne() {
        frame.setRolls(7, 0);
        assertEquals(7, frame.getRollOne());
    }

    @Test
    @DisplayName("Extract bonus roll 1")
    void TestGetRollThree() {
        frame.setRolls(10, 0, 4);
        assertEquals(4, frame.getRollThree());
    }

    @Test
    @DisplayName("Bonus rolls default to 0")
    void Test0BonusRolls() {
        frame.setRolls(2, 3);
        assertEquals(0, frame.getRollThree());
    }

    @Test
    @DisplayName("Can set one bonus roll")
    void Test1BonusRoll() {
        frame.setRolls(1, 9, 5);
        assertEquals(5, frame.getRollThree());
    }

    @Test
    @DisplayName("Can set two bonus rolls")
    void Test2BonusRolls() {
        frame.setRolls(10, 2, 3);
        assertEquals(5, frame.sumRollsTwoAndThree());
    }

    @Test
    @DisplayName("Identify all spares")
    void TestIsSpare() {
        for (int count = 0; count < 10; count++) {
            frame.setRolls(count, 10 - count);
            assertTrue(frame.isSpare());
        }
    }

    @Test
    @DisplayName("Regular frames are not spares")
    void TestRegularIsNotSpare() {
        frame.setRolls(2, 3);
        assertFalse(frame.isSpare());
    }

    @Test
    @DisplayName("Strikes are not spares")
    void TestStrikeIsNotSpare() {
        frame.setRolls(10, 0);
        assertFalse(frame.isSpare());
    }

    @Test
    @DisplayName("Identify a strike")
    void TestIsStrike() {
        frame.setRolls(10, 0);
        assertTrue(frame.isStrike());
    }

}
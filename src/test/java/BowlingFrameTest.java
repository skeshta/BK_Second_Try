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
    @DisplayName("Extract roll 1")
    void TestGetRollOne() {
        frame.setRolls(7, 0);
        assertEquals(7, frame.getRollOne());
    }

    @Test
    @DisplayName("Extract roll 3")
    void TestGetRollThree() {
        frame.setRolls(10, 0, 4);
        assertEquals(4, frame.getRollThree());
    }

    @Test
    @DisplayName("Third roll defaults to 0")
    void Test0BonusRolls() {
        frame.setRolls(2, 3);
        assertEquals(0, frame.getRollThree());
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
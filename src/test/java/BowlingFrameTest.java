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
    @DisplayName("Rolls sum correctly")
    void TestSumRolls() {
        frame.set(1, 4);
        assertEquals(5, frame.sumRolls());
    }

    @Test
    @DisplayName("Extract roll 1 correctly")
    void TestExtractRollOne() {
        frame.set(7, 0);
        assertEquals(7, frame.getRollOne());
    }

    @Test
    @DisplayName("All spares are identified correctly")
    void TestIsSpare() {
        for (int count = 0; count < 10; count++) {
            frame.set(count, 10 - count);
            assertEquals(true, frame.isSpare());
        }
    }

}
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
    void TestRollsSumCorrectly() {
        frame.set(1, 4);
        assertEquals(5, frame.sumRolls());
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
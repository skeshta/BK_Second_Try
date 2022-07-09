import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingFrameTest {
    private BowlingFrame frame;
    private final int error_value = -2147483648;

    @BeforeEach
    void setUp() {
        frame = new BowlingFrame();
    }

    @AfterEach
    void tearDown() {
        frame = null;
    }

    @Test
    @DisplayName("A frame with 1 and 7 has score 8")
    void TestSingleFrameNoStrikeNoSpare() {
        frame.setFrame(1, 7);
        assertEquals(8, frame.frameSum());
    }

    @Test
    @DisplayName("Both rolls must be <= 10")
    void TestNumbersTooHigh() {
        frame.setFrame(4, 9);
        assertEquals(error_value , frame.frameSum());
    }

    @Test
    @DisplayName("Both rolls must be >= 0")
    void TestNumbersTooLow() {
        frame.setFrame(-4, 9);
        assertEquals(error_value , frame.frameSum());
    }

}
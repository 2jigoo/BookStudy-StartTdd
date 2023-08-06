package chap02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        assertEquals(3, result);    // false -> throw new AssertionFailedError

        int result2 = Calculator.plus(4, 1);
        assertEquals(5, result2);
    }
}
